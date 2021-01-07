package com.esgi.nova.query.resource.query_handlers

import com.esgi.nova.core_api.resources.queries.FindAllResourcesWithTranslationIdsByLanguageIdQuery
import com.esgi.nova.core_api.resources.views.ResourceWithTranslationIdsView
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllResourcesWithTranslationIdsByLanguageIdHandler(private val resourceRepository: ResourceRepository) {

    @QueryHandler
    fun handle(query: FindAllResourcesWithTranslationIdsByLanguageIdQuery): List<ResourceWithTranslationIdsView> {
        return resourceRepository.findAllByResourceTranslationsLanguageId(
            languageId = query.languageId.toUUID()
        )
            .map { it.toResourceWithTranslationIdsView() }
    }
}

