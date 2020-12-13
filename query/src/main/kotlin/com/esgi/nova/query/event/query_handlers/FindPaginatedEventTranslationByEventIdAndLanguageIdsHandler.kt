package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindPaginatedEventTranslationByEventIdAndLanguageIdsQuery
import com.esgi.nova.core_api.events.views.EventTranslationView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.event_translation.EventTranslationRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedEventTranslationByEventIdAndLanguageIdsHandler(private val eventTranslationRepository: EventTranslationRepository) {
    @QueryHandler
    fun handle(query: FindPaginatedEventTranslationByEventIdAndLanguageIdsQuery): PageBase<EventTranslationView> {
        return eventTranslationRepository.findEventTranslationByEventIdAndLanguageIdIn(
            query.eventId.toUUID(),
            query.languageIds.map { it.toUUID() },
            query.toPageable()
        )
            .map { it.toEventTranslationView() }
            .toStaticPage(query)
    }
}