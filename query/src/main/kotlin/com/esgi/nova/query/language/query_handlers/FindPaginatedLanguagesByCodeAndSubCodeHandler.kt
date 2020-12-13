package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.FindPaginatedLanguagesByCodeAndSubCodeQuery
import com.esgi.nova.core_api.languages.queries.views.LanguageView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedLanguagesByCodeAndSubCodeHandler(private val languageRepository: LanguageRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedLanguagesByCodeAndSubCodeQuery): PageBase<LanguageView> {
        return languageRepository
                .findAllByCodeStartingWithAndSubCodeStartingWith(code = query.code, subCode = query.subCode, pageable = query.toPageable())
                .map { it.toLanguageView() }
                .toStaticPage(query)

    }
}