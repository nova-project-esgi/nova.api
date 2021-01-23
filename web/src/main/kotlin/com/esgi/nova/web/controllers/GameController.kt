package com.esgi.nova.web.controllers

import com.esgi.nova.application.pagination.PageMetadata
import com.esgi.nova.application.pagination.PaginationDefault
import com.esgi.nova.application.services.events.models.TranslatedEventsWithBackgroundDto
import com.esgi.nova.application.services.games.GamesService
import com.esgi.nova.application.services.games.exceptions.GameNotFoundByIdException
import com.esgi.nova.application.services.games.models.GameForCreation
import com.esgi.nova.application.services.games.models.GameForUpdate
import com.esgi.nova.core_api.games.views.GameStateView
import com.esgi.nova.core_api.games.views.GameView
import com.esgi.nova.core_api.games.views.LeaderBoardGameView
import com.esgi.nova.web.content_negociation.CustomMediaType
import com.esgi.nova.web.extensions.toPageMetadata
import com.esgi.nova.web.io.output.Message
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.util.*
import javax.servlet.ServletContext

@RestController
@RequestMapping("games")
open class GameController constructor(
    private val gameService: GamesService,
    private val context: ServletContext
) {
    @GetMapping("{id}")
    fun getOneById(@PathVariable id: UUID): ResponseEntity<GameView> {
        return ResponseEntity.ok(gameService.findOneGameViewById(id))
    }

    @DeleteMapping("{id}")
    fun deleteOneById(@PathVariable id: UUID): ResponseEntity<Message> {
        gameService.deleteOneById(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping()
    fun deleteAll(): ResponseEntity<Message> {
        gameService.deleteAll()
        return ResponseEntity.noContent().build()
    }

    @PostMapping()
    open fun createOne(@RequestBody game: GameForCreation): ResponseEntity<Message> {
        val id = gameService.createGame(game)
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(GameController::class.java, "getOneById", id).build().toUri()
            )
            .build()
    }


    @PutMapping("{id}")
    open fun updateGame(@PathVariable id: UUID, @RequestBody game: GameForUpdate): ResponseEntity<Message> {
        try{
            gameService.updateGame(game, id)
        } catch (e: GameNotFoundByIdException){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity
            .noContent()
            .build()
    }

    @GetMapping("{id}/daily", produces = [CustomMediaType.Application.TranslatedEvent])
    fun getOneDailyEvent(
        @PathVariable("id") id: UUID,
        @RequestParam(
            value = "language",
            required = true
        ) language: String
    ): ResponseEntity<TranslatedEventsWithBackgroundDto?> {
        gameService.getOneDailyEvent(
            language,
            id,
            MvcUriComponentsBuilder.fromController(EventController::class.java).toUriString()
        )?.let { dailyEvent -> return ResponseEntity.ok(dailyEvent) }
        return ResponseEntity.notFound().build()
    }

    @GetMapping(produces = [CustomMediaType.Application.LeaderBoardGame])
    open fun getPaginatedLeaderBoardGamesOrderedByEventCount(
        @RequestParam(value = "page", required = false, defaultValue = "${PaginationDefault.PAGE}") page: Int,
        @RequestParam(value = "size", required = false, defaultValue = "${PaginationDefault.SIZE}") size: Int,
        @RequestParam(value = "difficultyId", required = false) difficultyId: UUID?
    ): PageMetadata<LeaderBoardGameView> {
        return gameService.getPaginatedLeaderBoardGamesOrderedByEventCount(
            page,
            size,
            difficultyId
        ).toPageMetadata()
    }

    @GetMapping(produces = [CustomMediaType.Application.GameState])
    open fun loadLastActiveGameForUser(
        @RequestParam(
            name = "username",
            required = true
        ) username: String
    ): ResponseEntity<GameStateView?> {
        val game = gameService.findLastActiveGameForUser(username)
        if (game != null) {
            return ResponseEntity.ok(game)
        }
        return ResponseEntity.notFound().build()
    }


}