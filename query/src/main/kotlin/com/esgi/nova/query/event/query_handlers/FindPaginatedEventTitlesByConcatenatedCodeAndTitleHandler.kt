package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindPaginatedEventTitlesByConcatenatedCodeAndTitleQuery
import com.esgi.nova.core_api.events.views.EventTranslationTitleView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.event_translation.EventTranslation
import com.esgi.nova.query.event_translation.EventTranslationRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
open class FindPaginatedEventTitlesByConcatenatedCodeAndTitleHandler(private val eventTranslationRepository: EventTranslationRepository) {
    @QueryHandler
    fun handle(query: FindPaginatedEventTitlesByConcatenatedCodeAndTitleQuery): PageBase<EventTranslationTitleView>{
        val codes = query.concatenatedCode.split("-")
        var events: Page<EventTranslation>? = null
        when (codes.size) {
            1 -> events = eventTranslationRepository.findEventTranslationByLanguageCodeAndTitleStartingWith(
                codes[0],
                query.title,
                query.toPageable()
            )
            2 -> events =
                eventTranslationRepository.findEventTranslationByLanguageCodeAndLanguageSubCodeAndTitleStartingWith(
                    codes[0],
                    codes[1],
                    query.title,
                    query.toPageable()
                )
        }
        events?.let { return events.map { it.toEventTranslationTitleView() }.toStaticPage(query) }
        return PageBase.emptyPage()
    }
}