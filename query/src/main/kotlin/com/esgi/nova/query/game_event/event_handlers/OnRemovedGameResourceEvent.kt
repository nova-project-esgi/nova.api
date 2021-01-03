package com.esgi.nova.query.game_event.event_handlers

import com.esgi.nova.core_api.games.events.RemovedGameResourceEvent
import com.esgi.nova.query.game_resource.GameResourceId
import com.esgi.nova.query.game_resource.GameResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnRemovedGameResourceEvent constructor(private val gameResourceRepository: GameResourceRepository) {

    @EventHandler
    fun on(event: RemovedGameResourceEvent) {
        gameResourceRepository.deleteById(
            GameResourceId(
                gameId = event.gameId.toUUID(),
                resourceId = event.resourceId.toUUID()
            )
        )
    }
}