package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindPaginatedTranslatedEventByLanguageCodeSubCodeAndTitleQuery
import com.esgi.nova.core_api.events.views.TranslatedEventView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.event_translation.EventTranslationRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedTranslatedEventByLanguageCodeSubCodeAndTitleHandler(private val eventTranslationRepository: EventTranslationRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedTranslatedEventByLanguageCodeSubCodeAndTitleQuery): PageBase<TranslatedEventView> {
        return eventTranslationRepository
                .findEventTranslationByLanguageCodeAndLanguageSubCodeAndTitleStartingWith(query.code, query.subCode, query.title, query.toPageable())
                .map { it.toTranslatedEventView() }
                .toStaticPage(query)
    }
}