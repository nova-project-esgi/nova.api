package com.esgi.nova.query.game_resource.event_handlers

import com.esgi.nova.core_api.games.events.UpdatedGameResourceEvent
import com.esgi.nova.query.game_resource.GameResourceId
import com.esgi.nova.query.game_resource.GameResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnUpdatedGameResourceEvent constructor(private val gameResourceRepository: GameResourceRepository) {

    @EventHandler
    fun on(event: UpdatedGameResourceEvent) {
        gameResourceRepository.getOne(
            GameResourceId(
                gameId = event.gameId.toUUID(),
                resourceId = event.resourceId.toUUID()
            )
        ).let { gameResource ->
            gameResource.total = event.total
            gameResourceRepository.saveAndFlush(gameResource)
        }

    }
}