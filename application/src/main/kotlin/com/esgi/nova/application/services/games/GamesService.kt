package com.esgi.nova.application.services.games

import com.esgi.nova.application.extensions.queryPage
import com.esgi.nova.application.pagination.Link
import com.esgi.nova.application.pagination.Relation
import com.esgi.nova.application.services.events.models.TranslatedEventsWithBackgroundDto
import com.esgi.nova.application.services.games.exceptions.GameNotFoundByIdException
import com.esgi.nova.application.services.games.models.GameForCreation
import com.esgi.nova.application.services.games.models.GameForUpdate
import com.esgi.nova.application.services.resources.ResourcesService
import com.esgi.nova.application.services.users.UsersService
import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.events.queries.FindAllEventsByGameIdOrderByLinkTimeDescQuery
import com.esgi.nova.core_api.events.queries.FindAllTranslatedEventsByLanguageFrequencyAndActiveStateQuery
import com.esgi.nova.core_api.events.queries.FindPaginatedLeaderBoardGamesByDifficultyOrderedByEventCountQuery
import com.esgi.nova.core_api.events.queries.FindPaginatedLeaderBoardGamesOrderedByEventCountQuery
import com.esgi.nova.core_api.events.views.EventView
import com.esgi.nova.core_api.events.views.TranslatedEventView
import com.esgi.nova.core_api.games.GameEventIdentifier
import com.esgi.nova.core_api.games.GameIdentifier
import com.esgi.nova.core_api.games.UpdateGameStatusCommand
import com.esgi.nova.core_api.games.commands.AddGameResourceCommand
import com.esgi.nova.core_api.games.commands.CreateGameCommand
import com.esgi.nova.core_api.games.commands.DeleteGameCommand
import com.esgi.nova.core_api.games.commands.UpdateGameCommand
import com.esgi.nova.core_api.games.dtos.GameEventEditionDto
import com.esgi.nova.core_api.games.dtos.GameResourceEditionDto
import com.esgi.nova.core_api.games.queries.FindAllActiveGamesIdsByUserIdQuery
import com.esgi.nova.core_api.games.queries.FindLastActiveGameResumeByUserIdQuery
import com.esgi.nova.core_api.games.queries.FindOneGameViewByIdQuery
import com.esgi.nova.core_api.games.queries.GetAllGameIdsQuery
import com.esgi.nova.core_api.games.views.GameStateView
import com.esgi.nova.core_api.games.views.GameView
import com.esgi.nova.core_api.games.views.LeaderBoardGameView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resources.ResourceIdentifier
import com.esgi.nova.core_api.user.UserIdentifier
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByUsernameException
import com.esgi.nova.core_api.user.queries.FindUserByUsernameQuery
import com.esgi.nova.core_api.user.views.UserResume
import io.netty.handler.codec.http.HttpMethod
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.extensions.kotlin.queryMany
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
open class GamesService(
    private val queryGateway: QueryGateway,
    private val commandGateway: CommandGateway,
    private val resourcesUsesCases: ResourcesService,
    private val userService: UsersService
) {

    fun findOneGameViewById(id: UUID): GameView {
        return queryGateway.query<GameView?, FindOneGameViewByIdQuery>(
            FindOneGameViewByIdQuery(GameIdentifier(id.toString()))
        ).join() ?: throw GameNotFoundByIdException()
    }

    fun deleteAll() {
        queryGateway.queryMany<UUID, GetAllGameIdsQuery>(GetAllGameIdsQuery()).join().forEach { id ->
            deleteOneById(id)
        }
    }

    fun deleteOneById(id: UUID) {
        commandGateway.sendAndWait<GameIdentifier>(DeleteGameCommand(gameId = GameIdentifier(id.toString())))
    }

    fun createGame(game: GameForCreation): UUID {
        val user =
            queryGateway.query<UserResume?, FindUserByUsernameQuery>(FindUserByUsernameQuery(game.username)).join()
                ?: throw UserNotFoundByUsernameException()
        val resources = resourcesUsesCases.findAllResourcesByDifficulty(game.difficultyId)

        queryGateway.queryMany<UUID, FindAllActiveGamesIdsByUserIdQuery>(
            FindAllActiveGamesIdsByUserIdQuery(
                userId = UserIdentifier(
                    user.id.toString()
                )
            )
        ).join().forEach { activeGameId ->
            commandGateway.sendAndWait<GameIdentifier>(
                UpdateGameStatusCommand(gameId = GameIdentifier(activeGameId.toString()), isEnded = true)
            )
        }

        val gameId = commandGateway.sendAndWait<GameIdentifier>(
            CreateGameCommand(
                gameId = GameIdentifier(),
                difficultyId = DifficultyIdentifier(game.difficultyId.toString()),
                userId = UserIdentifier(user.id.toString())
            )
        )
        resources.forEach { r ->
            commandGateway.sendAndWait<GameIdentifier>(
                AddGameResourceCommand(
                    gameId = gameId,
                    total = r.startValue,
                    resourceId = ResourceIdentifier(r.resourceId.toString())
                )
            )
        }

        return gameId.toUUID()
    }

    fun updateGame(game: GameForUpdate, id: UUID) {
        commandGateway.sendAndWait<GameIdentifier>(
            UpdateGameCommand(
                gameId = GameIdentifier(id.toString()),
                isEnded = game.isEnded ?: false,
                resources = game.resources.map {
                    GameResourceEditionDto(
                        resourceId = ResourceIdentifier(it.resourceId.toString()),
                        total = it.total
                    )
                },
                duration = game.duration,
                events = game.events.map { e ->
                    GameEventEditionDto(
                        eventId = EventIdentifier(e.eventId.toString()),
                        linkTime = e.linkTime ?: LocalDateTime.now(),
                        id = GameEventIdentifier(e.id.toString())
                    )
                }
            )
        )
    }

    fun getOneDailyEvent(
        language: String,
        gameId: UUID,
        backgroundBaseUrl: String
    ): TranslatedEventsWithBackgroundDto? {
        val gameEvents = queryGateway.queryMany<EventView, FindAllEventsByGameIdOrderByLinkTimeDescQuery>(
            FindAllEventsByGameIdOrderByLinkTimeDescQuery(
                gameId = GameIdentifier(gameId.toString())
            )
        ).join()
        val dailyEvents =
            queryGateway.queryMany<TranslatedEventView, FindAllTranslatedEventsByLanguageFrequencyAndActiveStateQuery>(
                FindAllTranslatedEventsByLanguageFrequencyAndActiveStateQuery(
                    language = language,
                    isDaily = true, isActive = true
                )
            ).join()

        dailyEvents.forEach { dE ->
            if (gameEvents.take(3).none { e -> e.id == dE.id }) {
                return TranslatedEventsWithBackgroundDto(
                    id = dE.id,
                    language = dE.language,
                    backgroundUrl = Link(Relation.ASSET, "$backgroundBaseUrl/${dE.id}/background", HttpMethod.GET),
                    choices = dE.choices,
                    title = dE.title,
                    description = dE.description
                )
            }
        }
        return null
    }

    fun getPaginatedLeaderBoardGamesOrderedByEventCount(
        page: Int,
        size: Int,
        difficultyId: UUID?
    ): PageBase<LeaderBoardGameView> {
        difficultyId?.let {
            return queryGateway.queryPage<LeaderBoardGameView, FindPaginatedLeaderBoardGamesByDifficultyOrderedByEventCountQuery>(
                FindPaginatedLeaderBoardGamesByDifficultyOrderedByEventCountQuery(
                    page = page,
                    size = size,
                    difficultyId = DifficultyIdentifier(difficultyId.toString())
                )
            ).join()
        }
        return queryGateway.queryPage<LeaderBoardGameView, FindPaginatedLeaderBoardGamesOrderedByEventCountQuery>(
            FindPaginatedLeaderBoardGamesOrderedByEventCountQuery(page = page, size = size)
        ).join()
    }

    fun findLastActiveGameForUser(username: String): GameStateView? {
        return queryGateway.query<GameStateView?, FindLastActiveGameResumeByUserIdQuery>(
            FindLastActiveGameResumeByUserIdQuery(userId = UserIdentifier(userService.getResumeByUsername(username).id.toString()))
        ).join()

    }
}

