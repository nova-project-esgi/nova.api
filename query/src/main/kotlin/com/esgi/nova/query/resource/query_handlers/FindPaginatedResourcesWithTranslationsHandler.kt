package com.esgi.nova.query.resource.query_handlers

import com.esgi.nova.core_api.difficulty.queries.FindPaginatedDifficultyWithAvailableActionsViewsByConcatenatedCodesAndNameQuery
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resources.queries.FindPaginatedResourcesWithTranslationsQuery
import com.esgi.nova.core_api.resources.views.ResourceWithAvailableActionsView
import com.esgi.nova.query.difficulty.DifficultyRepository
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedResourcesWithTranslationsHandler(private val resourceRepository: ResourceRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedResourcesWithTranslationsQuery): PageBase<ResourceWithAvailableActionsView> {
        return resourceRepository.findAll(query.toPageable())
            .map { it.toResourceWithAvailableActionsView()}
            .toStaticPage(query)
    }
}
