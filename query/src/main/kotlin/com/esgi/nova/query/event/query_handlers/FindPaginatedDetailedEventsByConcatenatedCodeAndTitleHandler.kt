package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindPaginatedDetailedEventsByConcatenatedCodeAndTitleQuery
import com.esgi.nova.core_api.events.views.DetailedEventView
import com.esgi.nova.core_api.events.views.TranslatedEventView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.event.EventRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedDetailedEventsByConcatenatedCodeAndTitleHandler(private val eventRepository: EventRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedDetailedEventsByConcatenatedCodeAndTitleQuery): PageBase<DetailedEventView> {
        val events =
            eventRepository.findEventsByEventTranslationsTitleStartingWithAndEventTranslationsLanguageConcatenatedCodesStartingWith(
                query.title,
                query.concatenatedCode,
                query.toPageable()
            )
        return events.map{it.toDetailedEvent()}.toStaticPage(query)
    }
}