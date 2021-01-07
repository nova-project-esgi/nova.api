package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.FindPaginatedLanguagesWithAvailableActionsByCodeQuery
import com.esgi.nova.core_api.languages.views.LanguageViewWithAvailableActions
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.choice.ChoiceRepository
import com.esgi.nova.query.event.EventRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.language.LanguageRepository
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedLanguagesWithAvailableActionsByCodeHandler(
    private val languageRepository: LanguageRepository,
    private val eventRepository: EventRepository,
    private val choiceRepository: ChoiceRepository,
    private val resourceRepository: ResourceRepository
) {

    @QueryHandler
    fun handle(query: FindPaginatedLanguagesWithAvailableActionsByCodeQuery): PageBase<LanguageViewWithAvailableActions> {
        val eventCount = eventRepository.count()
        val choiceCount = choiceRepository.count()
        val resourceCount = resourceRepository.count()
        return languageRepository
            .findAllByCode(query.code, query.toPageable()).map { language ->
                val canSetDefault = eventCount.toInt() == language.eventTranslations.size
                        && choiceCount.toInt() == language.choiceTranslations.size
                        && resourceCount.toInt() == language.resourceTranslations.size
                language.toLanguageViewWithAvailableActions(canSetDefault)
            }
            .toStaticPage(query)
    }

}