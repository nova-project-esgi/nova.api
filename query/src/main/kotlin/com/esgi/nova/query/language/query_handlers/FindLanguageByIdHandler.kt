package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.FindLanguageByIdQuery
import com.esgi.nova.core_api.languages.views.LanguageView
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class FindLanguageByIdHandler(private val languageRepository: LanguageRepository) {

    @QueryHandler
    fun handle(query: FindLanguageByIdQuery): LanguageView? {
        return languageRepository.findByIdOrNull(query.id.toUUID())?.toLanguageView()
    }
}