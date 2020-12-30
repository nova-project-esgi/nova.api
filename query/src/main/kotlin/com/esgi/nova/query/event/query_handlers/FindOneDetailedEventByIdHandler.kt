package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindOneDetailedEventByIdQuery
import com.esgi.nova.core_api.events.views.DetailedEventView
import com.esgi.nova.query.event.EventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class FindOneDetailedEventByIdHandler(private val eventRepository: EventRepository) {

    @QueryHandler
    fun handle(query: FindOneDetailedEventByIdQuery): DetailedEventView? {
        return eventRepository.findByIdOrNull(query.id.toUUID())?.toDetailedEvent()
    }
}