package com.esgi.nova.query.game.event_handlers

import com.esgi.nova.core_api.games.events.UpdatedGameStatusEvent
import com.esgi.nova.query.game.GameRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnUpdatedGameStatusEvent constructor(private val gameRepository: GameRepository) {

    @EventHandler
    fun on(event: UpdatedGameStatusEvent) {
        gameRepository.getOne(event.gameId.toUUID()).let { game ->
            game.isEnded = event.isEnded
            gameRepository.saveAndFlush(game)
        }
    }
}