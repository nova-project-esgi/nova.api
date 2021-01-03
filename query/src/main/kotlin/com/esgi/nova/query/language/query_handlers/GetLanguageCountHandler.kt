package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.GetLanguageCountQuery
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class GetLanguageCountHandler(
    private val languageRepository: LanguageRepository
) {

    @QueryHandler
    fun handle(query: GetLanguageCountQuery): Int {
        return languageRepository.count().toInt()

    }
}