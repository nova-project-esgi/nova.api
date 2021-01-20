package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindOneTranslatedEventByLanguageAndId
import com.esgi.nova.core_api.events.views.TranslatedEventView
import com.esgi.nova.query.event.EventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class FindOneTranslatedEventByLanguageAndIdHandler(private val eventRepository: EventRepository) {

    @QueryHandler
    fun handle(query: FindOneTranslatedEventByLanguageAndId): TranslatedEventView? {
        return eventRepository.findByIdOrNull(query.eventId.toUUID())?.toTranslatedEvent(query.language)
    }
}