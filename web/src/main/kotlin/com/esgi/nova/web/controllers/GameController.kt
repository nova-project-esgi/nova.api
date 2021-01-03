package com.esgi.nova.web.controllers

import com.esgi.nova.application.uses_cases.games.GameForCreation
import com.esgi.nova.application.uses_cases.games.GameForUpdate
import com.esgi.nova.application.uses_cases.games.GamesUseCases
import com.esgi.nova.core_api.games.views.GameView
import com.esgi.nova.web.io.output.Message
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import java.util.*
import javax.servlet.ServletContext

@RestController
@RequestMapping("games")
open class GameController constructor(
    private val gameUseCases: GamesUseCases,
    private val context: ServletContext
) {
    @GetMapping("{id}")
    fun getOneById(@PathVariable id: UUID): ResponseEntity<GameView> {
        return ResponseEntity.ok(gameUseCases.findOneGameViewById(id))
    }

    @DeleteMapping("{id}")
    fun deleteOneById(@PathVariable id: UUID): ResponseEntity<Message> {
        gameUseCases.deleteOneById(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping()
    open fun createOne(@RequestBody game: GameForCreation): ResponseEntity<Message> {
        val id = gameUseCases.createGame(game)
        return ResponseEntity
            .created(
                MvcUriComponentsBuilder.fromMethodName(GameController::class.java, "getOneById", id).build().toUri()
            )
            .build()
    }


    @PutMapping("{id}")
    open fun createOne(@PathVariable id: UUID, @RequestBody game: GameForUpdate): ResponseEntity<Message> {
        gameUseCases.updateGame(game, id)
        return ResponseEntity
            .noContent()
            .build()
    }
}