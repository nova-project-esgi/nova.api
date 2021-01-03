package com.esgi.nova.query.game_resource.event_handlers

import com.esgi.nova.core_api.games.events.AddedGameResourceEvent
import com.esgi.nova.query.game.GameRepository
import com.esgi.nova.query.game_resource.GameResource
import com.esgi.nova.query.game_resource.GameResourceId
import com.esgi.nova.query.game_resource.GameResourceRepository
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnAddedGameResourceEvent constructor(
    private val gameResourceRepository: GameResourceRepository,
    private val resourceRepository: ResourceRepository,
    private val gameRepository: GameRepository
) {

    @EventHandler
    fun on(event: AddedGameResourceEvent) {
        val game = gameRepository.getOne(event.gameId.toUUID())
        val resource = resourceRepository.getOne(event.resourceId.toUUID())
        gameResourceRepository.saveAndFlush(
            GameResource(
                GameResourceId(
                    gameId = event.gameId.toUUID(),
                    resourceId = event.resourceId.toUUID()
                ), total = event.total, resource = resource, game = game
            )
        )

    }
}