package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindPaginatedEventTitlesByConcatenatedCodeAndTitleQuery
import com.esgi.nova.core_api.events.views.EventTranslationTitleView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.event_translation.EventTranslationRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedEventTitlesByConcatenatedCodeAndTitleHandler(private val eventTranslationRepository: EventTranslationRepository) {
    @QueryHandler
    fun handle(query: FindPaginatedEventTitlesByConcatenatedCodeAndTitleQuery): PageBase<EventTranslationTitleView> {
        return eventTranslationRepository.findEventTranslationByLanguageConcatenatedCodesAndTitleStartingWith(
            concatenatedCode = query.concatenatedCode,
            title = query.title,
            pageable = query.toPageable()
        ).map { it.toEventTranslationTitleView() }.toStaticPage(query)
    }
}