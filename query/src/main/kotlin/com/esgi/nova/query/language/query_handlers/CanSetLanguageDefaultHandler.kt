package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.CanSetLanguageDefaultQuery
import com.esgi.nova.query.choice.ChoiceRepository
import com.esgi.nova.query.event.EventRepository
import com.esgi.nova.query.language.LanguageRepository
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class CanSetLanguageDefaultHandler(
    private val languageRepository: LanguageRepository,
    private val eventRepository: EventRepository,
    private val choiceRepository: ChoiceRepository,
    private val resourceRepository: ResourceRepository
) {

    @QueryHandler
    fun handle(query: CanSetLanguageDefaultQuery): Boolean {
        languageRepository.findByIdOrNull(query.languageId.toUUID())?.let {
            return eventRepository.count().toInt() == it.eventTranslations.size
                    && choiceRepository.count().toInt() == it.choiceTranslations.size
                    && resourceRepository.count().toInt() == it.resourceTranslations.size
        }
        return false;
    }
}