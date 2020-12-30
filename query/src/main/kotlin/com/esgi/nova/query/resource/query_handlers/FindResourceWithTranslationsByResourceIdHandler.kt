package com.esgi.nova.query.resource.query_handlers

import com.esgi.nova.core_api.resource_translation.queries.FindAllResourceTranslationsByResourceIdQuery
import com.esgi.nova.core_api.resources.views.ResourceWithAvailableActionsView
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class FindResourceWithTranslationsByResourceIdHandler(private val resourceRepository: ResourceRepository) {
    @QueryHandler
    fun handle(query: FindAllResourceTranslationsByResourceIdQuery): ResourceWithAvailableActionsView? {
        return resourceRepository.findByIdOrNull(query.id.toUUID())?.toResourceWithAvailableActionsView()
    }
}