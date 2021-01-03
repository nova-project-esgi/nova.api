package com.esgi.nova.query.game_event.event_handlers

import com.esgi.nova.core_api.games.events.RemovedGameEventEvent
import com.esgi.nova.query.game_event.GameEventRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnRemovedGameEventEvent constructor(private val gameEventRepository: GameEventRepository) {

    @EventHandler
    fun on(event: RemovedGameEventEvent) {
        gameEventRepository.findAllByEventIdAndGameId(eventId = event.eventId.toUUID(), gameId = event.gameId.toUUID())
            .forEach { gameEvent ->
                gameEventRepository.deleteById(gameEvent.id)
            }

    }
}

