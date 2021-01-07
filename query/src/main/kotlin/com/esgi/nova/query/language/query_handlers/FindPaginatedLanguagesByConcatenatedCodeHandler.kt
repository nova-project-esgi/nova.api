package com.esgi.nova.query.language.query_handlers

import com.esgi.nova.core_api.languages.queries.FindPaginatedLanguagesByConcatenatedCodeQuery
import com.esgi.nova.core_api.languages.views.LanguageView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.language.Language
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
open class FindPaginatedLanguagesByConcatenatedCodeHandler(private val languageRepository: LanguageRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedLanguagesByConcatenatedCodeQuery): PageBase<LanguageView> {
        val codes = query.concatenatedCode.split("-")
        var languages: Page<Language>? = null
        when (codes.size) {
            1 -> languages = languageRepository.findAllByCode(codes[0], query.toPageable())
            2 -> languages = languageRepository.findAllByCodeStartingWithAndSubCodeStartingWith(
                codes[0],
                codes[1],
                query.toPageable()
            )
        }
        languages?.let { return languages.map { it.toLanguageView() }.toStaticPage(query) }
        return PageBase.emptyPage()
    }
}