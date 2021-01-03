package com.esgi.nova.application.uses_cases.games

import com.esgi.nova.application.uses_cases.resources.ResourcesUseCases
import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.games.commands.*
import com.esgi.nova.core_api.games.queries.FindOneGameViewByIdQuery
import com.esgi.nova.core_api.games.views.GameView
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import com.esgi.nova.core_api.user.UserIdentifier
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByUsernameException
import com.esgi.nova.core_api.user.queries.FindUserByUsernameQuery
import com.esgi.nova.core_api.user.views.UserResume
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
open class GamesUseCases(
    private val queryGateway: QueryGateway,
    private val commandGateway: CommandGateway,
    private val resourcesUsesCases: ResourcesUseCases
) {

    fun findOneGameViewById(id: UUID): GameView {
        return queryGateway.query<GameView?, FindOneGameViewByIdQuery>(
            FindOneGameViewByIdQuery(GameIdentifier(id.toString()))
        ).join() ?: throw GameNotFoundByIdException()
    }

    fun deleteOneById(id: UUID) {
        commandGateway.sendAndWait<GameIdentifier>(DeleteGameCommand(gameId = GameIdentifier(id.toString())))
    }

    fun createGame(game: GameForCreation): UUID {
        val user =
            queryGateway.query<UserResume?, FindUserByUsernameQuery>(FindUserByUsernameQuery(game.username)).join()
                ?: throw UserNotFoundByUsernameException()
        val resources = resourcesUsesCases.findAllResourcesByDifficulty(game.difficultyId)
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
                        linkTime = e.linkTime ?: LocalDateTime.now()
                    )
                }
            )
        )
    }
}

