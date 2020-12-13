package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.FindLanguageByCodeAndSubCodeQuery
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindLanguageByCodeAndSubCodeHandler(private val languageRepository: LanguageRepository) {

    @QueryHandler
    fun handle(query: FindLanguageByCodeAndSubCodeQuery): LanguageView? {
        return languageRepository.findByCodeAndSubCode(query.code, query.subCode)?.toLanguageView()
    }
}