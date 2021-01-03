package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindAllEventsByGameIdQuery
import com.esgi.nova.core_api.events.views.EventView
import com.esgi.nova.query.game_event.GameEventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllEventsByGameIdHandler(private val gameEventRepository: GameEventRepository) {

    @QueryHandler
    fun handle(query: FindAllEventsByGameIdQuery): List<EventView> {
        return gameEventRepository.findAllByGameId(query.gameId.toUUID()).map { it.event.toEventView() }
    }
}

