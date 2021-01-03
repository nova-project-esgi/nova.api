package com.esgi.nova.query.difficulty.query_handlers

import com.esgi.nova.core_api.difficulty.queries.FindAllTranslatedDifficultiesByLanguageQuery
import com.esgi.nova.core_api.difficulty.views.TranslatedDifficultyView
import com.esgi.nova.query.difficulty.DifficultyRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllTranslatedDifficultiesByLanguageHandler(
    private val difficultyRepository: DifficultyRepository
) {

    @QueryHandler
    fun handle(query: FindAllTranslatedDifficultiesByLanguageQuery): List<TranslatedDifficultyView> {
        return difficultyRepository.findAll().map { it.toTranslatedDifficultyView(query.language) }
    }
}