package com.esgi.nova.query.resource.query_handlers

import com.esgi.nova.core_api.resources.queries.CanDeleteResourceByIdQuery
import com.esgi.nova.query.extensions.findNullableById
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class CanDeleteResourceByIdHandler(private val resourceRepository: ResourceRepository) {

    @QueryHandler
    fun handle(query: CanDeleteResourceByIdQuery): Boolean {
        resourceRepository.findNullableById(query.id.toUUID())?.let {
            return it.choiceResources.isEmpty() && it.gameResources.isEmpty()
        }
        return false;
    }
}

