package com.esgi.nova.query.game.event_handlers

import com.esgi.nova.core_api.games.events.UpdatedGameEvent
import com.esgi.nova.query.game.GameRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnUpdatedGameEvent constructor(private val gameRepository: GameRepository) {

    @EventHandler
    fun on(event: UpdatedGameEvent) {
        gameRepository.getOne(event.gameId.toUUID()).let { game ->
            game.duration = event.duration
            game.isEnded = event.isEnded
            gameRepository.saveAndFlush(game)
        }
    }
}