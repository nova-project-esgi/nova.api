package com.esgi.nova.query.difficulty_translation.query_handlers

import com.esgi.nova.core_api.difficulty.queries.FindPaginatedDifficultyViewsQuery
import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.difficulty.DifficultyRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedDifficultyViewsHandler(private val difficultyRepository: DifficultyRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedDifficultyViewsQuery): PageBase<DetailedDifficultyView> {
        return difficultyRepository.findAll(
            query.toPageable()
        ).map { it.toDetailedDifficultyView() }.toStaticPage(query)
    }
}