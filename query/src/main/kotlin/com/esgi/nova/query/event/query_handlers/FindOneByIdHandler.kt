package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindOneEventByIdQuery
import com.esgi.nova.core_api.events.views.EventView
import com.esgi.nova.query.event.EventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class FindOneByIdHandler(private val eventRepository: EventRepository) {

    @QueryHandler
    fun handle(query: FindOneEventByIdQuery): EventView? {
        return eventRepository.findByIdOrNull(query.id.toUUID())?.toEventView()
    }
}

