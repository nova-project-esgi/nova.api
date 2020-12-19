package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.AllLanguagesExistsByIdsQuery
import com.esgi.nova.core_api.languages.queries.FindLanguageByIdQuery
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class AllLanguagesExistsByIdsHandler(private val languageRepository: LanguageRepository) {

    @QueryHandler
    fun handle(query: AllLanguagesExistsByIdsQuery): Boolean{
        return languageRepository.findAllById(query.languageIds.map { it.toUUID() }).size == query.languageIds.size
    }
}