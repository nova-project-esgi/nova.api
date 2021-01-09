package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindActiveStoreEventsQuery
import com.esgi.nova.query.event.EventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
open class FindActiveStoreEventsHandler(private val eventRepository: EventRepository) {

    @QueryHandler
    fun handle(query: FindActiveStoreEventsQuery): List<UUID> {
        return eventRepository
            .findAllById(query.ids.map { it.toUUID() })
            .map { it.id }
    }
}