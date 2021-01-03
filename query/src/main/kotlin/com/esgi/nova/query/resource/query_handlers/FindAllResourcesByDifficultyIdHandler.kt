package com.esgi.nova.query.resource.query_handlers

import com.esgi.nova.core_api.resources.queries.FindAllResourcesByDifficultyIdQuery
import com.esgi.nova.core_api.resources.views.ResourceDifficultyView
import com.esgi.nova.query.difficulty_ressource.DifficultyResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllResourcesByDifficultyIdHandler(private val difficultyResourceRepository: DifficultyResourceRepository) {

    @QueryHandler
    fun handle(query: FindAllResourcesByDifficultyIdQuery): List<ResourceDifficultyView> {
        return difficultyResourceRepository.findAllByDifficultyId(query.difficultyId.toUUID())
            .map { it.toResourceDifficultyView() }
    }
}