package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindAllEventsByGameIdOrderByLinkTimeDescQuery
import com.esgi.nova.core_api.events.views.EventView
import com.esgi.nova.query.game_event.GameEventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllEventsByGameIdOrderByLinkTimeDescHandler(private val gameEventRepository: GameEventRepository) {

    @QueryHandler
    fun handle(query: FindAllEventsByGameIdOrderByLinkTimeDescQuery): List<EventView> {
        return gameEventRepository.findAllByGameIdOrderByLinkTimeDesc(query.gameId.toUUID()).map { it.event.toEventView() }
    }
}