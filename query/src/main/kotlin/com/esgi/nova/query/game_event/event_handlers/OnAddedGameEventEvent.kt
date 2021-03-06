package com.esgi.nova.query.game_event.event_handlers

import com.esgi.nova.core_api.games.events.AddedGameEventEvent
import com.esgi.nova.query.event.EventRepository
import com.esgi.nova.query.game.GameRepository
import com.esgi.nova.query.game_event.GameEvent
import com.esgi.nova.query.game_event.GameEventRepository
import org.axonframework.eventhandling.EventHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
open class OnAddedGameEventEvent constructor(
    private val gameRepository: GameRepository,
    private val eventRepository: EventRepository,
    private val gameEventRepository: GameEventRepository
) {
    var logger: Logger = LoggerFactory.getLogger(OnAddedGameEventEvent::class.java)
    @EventHandler
    fun on(event: AddedGameEventEvent) {
        logger.info("ADD GAME EVENT $event")
        val game = gameRepository.getOne(event.gameId.toUUID())
        val existingEvent = eventRepository.getOne(event.eventId.toUUID())
        gameEventRepository.saveAndFlush(GameEvent(game = game, event = existingEvent, linkTime = event.linkTime))
    }
}