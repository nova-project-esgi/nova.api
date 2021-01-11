package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.GetAllLanguagesQuery
import com.esgi.nova.core_api.languages.views.LanguageView
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class GetAllLanguagesHandler(private val languageRepository: LanguageRepository) {

    @QueryHandler
    fun handle(query: GetAllLanguagesQuery): List<LanguageView> {
        return languageRepository.findAll().map { it.toLanguageView() }
    }
}