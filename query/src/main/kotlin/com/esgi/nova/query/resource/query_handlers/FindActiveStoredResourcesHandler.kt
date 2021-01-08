package com.esgi.nova.query.resource.query_handlers

import com.esgi.nova.core_api.resources.queries.FindActiveStoredResourcesQuery
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
open class FindActiveStoredResourcesHandler (private val resourceRepository: ResourceRepository) {

    @QueryHandler
    fun handle(query: FindActiveStoredResourcesQuery): List<UUID> {
        return resourceRepository.findAllById(query.ids.map{ it.toUUID() }).map { it.id }
    }

}