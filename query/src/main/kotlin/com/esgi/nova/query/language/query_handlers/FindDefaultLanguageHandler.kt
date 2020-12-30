package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.FindDefaultLanguageQuery
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindDefaultLanguageHandler(private val languageRepository: LanguageRepository) {

    @QueryHandler
    fun handle(query: FindDefaultLanguageQuery): LanguageView? {
        return languageRepository.findAllByIsDefault(true).firstOrNull()?.let {
            return it.toLanguageView()
        }
    }
}


