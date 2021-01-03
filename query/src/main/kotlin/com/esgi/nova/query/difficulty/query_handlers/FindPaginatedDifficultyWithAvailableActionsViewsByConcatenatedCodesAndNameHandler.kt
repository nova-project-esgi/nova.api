package com.esgi.nova.query.difficulty.query_handlers

import com.esgi.nova.core_api.difficulty.queries.FindPaginatedDifficultyWithAvailableActionsViewsByConcatenatedCodesAndNameQuery
import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyWithAvailableActionsView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.difficulty.DifficultyRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedDifficultyWithAvailableActionsViewsByConcatenatedCodesAndNameHandler(
    private val difficultyRepository: DifficultyRepository,
    private val resourceRepository: ResourceRepository
) {

    @QueryHandler
    fun handle(query: FindPaginatedDifficultyWithAvailableActionsViewsByConcatenatedCodesAndNameQuery): PageBase<DetailedDifficultyWithAvailableActionsView> {
        val resourcesCount = resourceRepository.count()
        return difficultyRepository.findAllByDifficultyTranslationsNameStartingWithAndDifficultyTranslationsLanguageConcatenatedCodesStartingWith(
            name = query.name,
            language = query.concatenatedCodes,
            pageable = query.toPageable()
        )
            .map { it.toDifficultyWithAvailableActions(it.difficultyResources.size == resourcesCount.toInt()) }
            .toStaticPage(query)
    }
}

