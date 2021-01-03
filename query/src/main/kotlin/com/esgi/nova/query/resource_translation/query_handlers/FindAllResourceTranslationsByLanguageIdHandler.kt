package com.esgi.nova.query.resource_translation.query_handlers

import com.esgi.nova.core_api.resources.queries.FindAllResourceTranslationsByLanguageIdQuery
import com.esgi.nova.core_api.resources.views.ResourceTranslationView
import com.esgi.nova.query.resource_translation.ResourceTranslationRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllResourceTranslationsByLanguageIdHandler(private val resourceTranslationRepository: ResourceTranslationRepository) {

    @QueryHandler
    fun handle(query: FindAllResourceTranslationsByLanguageIdQuery): List<ResourceTranslationView> {
        return resourceTranslationRepository.findAllByLanguageId(
            languageId = query.languageId.toUUID()
        ).map { it.toResourceTranslationView() }
    }
}