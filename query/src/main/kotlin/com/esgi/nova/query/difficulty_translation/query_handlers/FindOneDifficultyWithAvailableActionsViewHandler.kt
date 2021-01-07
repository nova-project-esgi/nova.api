package com.esgi.nova.query.difficulty_translation.query_handlers

import com.esgi.nova.core_api.difficulty.queries.FindOneDifficultyWithAvailableActionsViewQuery
import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyWithAvailableActionsView
import com.esgi.nova.query.difficulty.DifficultyRepository
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class FindOneDifficultyWithAvailableActionsViewHandler(
    private val difficultyRepository: DifficultyRepository,
    private val resourceRepository: ResourceRepository
) {

    @QueryHandler
    fun handle(query: FindOneDifficultyWithAvailableActionsViewQuery): DetailedDifficultyWithAvailableActionsView? {
        val resourcesCount = resourceRepository.count()
        difficultyRepository.findByIdOrNull(
            query.id.toUUID()
        )?.let {
            return it.toDifficultyWithAvailableActions(it.difficultyResources.size == resourcesCount.toInt())
        }
        return null
    }
}

