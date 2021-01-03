package com.esgi.nova.query.difficulty_translation.query_handlers

import com.esgi.nova.core_api.difficulty.queries.FindPaginatedDifficultyTranslationNameViewsByNameAndConcatenatedCodeQuery
import com.esgi.nova.core_api.difficulty.views.DifficultyTranslationNameView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.difficulty_translation.DifficultyTranslationRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedDifficultyTranslationNameViewsByNameAndConcatenatedCodHandler(private val difficultyTranslationRepository: DifficultyTranslationRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedDifficultyTranslationNameViewsByNameAndConcatenatedCodeQuery): PageBase<DifficultyTranslationNameView> {
        return difficultyTranslationRepository.findAllByNameStartingWithAndLanguageConcatenatedCodesStartingWith(
            name = query.name,
            concatenatedCode = query.concatenatedCode,
            pageable = query.toPageable()
        ).map { it.toDifficultyTranslationNameView() }.toStaticPage(query)
    }
}

