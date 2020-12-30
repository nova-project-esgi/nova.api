package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindPaginatedTranslatedEventTitleByLanguageCodeAndTitleQuery
import com.esgi.nova.core_api.events.views.EventTranslationTitleView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.event_translation.EventTranslationRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

//@Component
//open class FindPaginatedTranslatedEventTitleByLanguageCodeAndTitleHandler(private val eventTranslationRepository: EventTranslationRepository) {
//
//    @QueryHandler
//    fun handle(query: FindPaginatedTranslatedEventTitleByLanguageCodeAndTitleQuery): PageBase<EventTranslationTitleView> {
//        return eventTranslationRepository.findEventTranslationByLanguageConcatenatedCodesAndTitleStartingWith(query.code, query.title, query.toPageable())
//                .map { it.toEventTranslationTitleView() }
//                .toStaticPage(query)
//    }
//}