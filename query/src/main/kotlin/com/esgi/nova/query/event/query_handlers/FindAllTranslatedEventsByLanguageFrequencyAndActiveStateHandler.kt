package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindAllTranslatedEventsByLanguageFrequencyAndActiveStateQuery
import com.esgi.nova.core_api.events.views.TranslatedEventView
import com.esgi.nova.query.event.EventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllTranslatedEventsByLanguageFrequencyAndActiveStateHandler(private val eventRepository: EventRepository) {

    @QueryHandler
    fun handle(query: FindAllTranslatedEventsByLanguageFrequencyAndActiveStateQuery): List<TranslatedEventView> {
        return eventRepository.findAllByIsActiveAndIsDaily(query.isActive, query.isDaily).map {  it.toTranslatedEvent(query.language) }
    }
}