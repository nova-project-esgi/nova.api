package com.esgi.nova.query.resource_translation.query_handlers

import com.esgi.nova.core_api.resource_translation.queries.FindAllResourceTranslationsByResourceIdQuery
import com.esgi.nova.core_api.resource_translation.views.ResourceTranslationView
import com.esgi.nova.query.resource_translation.ResourceTranslationRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllResourceTranslationsByResourceIdHandler(private val resourceTranslationRepository: ResourceTranslationRepository) {

    @QueryHandler
    fun handle(query: FindAllResourceTranslationsByResourceIdQuery): List<ResourceTranslationView> {
        return resourceTranslationRepository.findAllByResourceId(query.id.toUUID()).map {
            it.toResourceTranslationView()
        }
    }
}

