package com.esgi.nova.query.game.event_handlers

import com.esgi.nova.core_api.games.events.DeletedGameEvent
import com.esgi.nova.query.game.GameRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnDeletedGameEvent constructor(private val gameRepository: GameRepository) {

    @EventHandler
    fun on(event: DeletedGameEvent) {
        gameRepository.deleteById(event.gameId.toUUID())
    }
}