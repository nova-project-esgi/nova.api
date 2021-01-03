package com.esgi.nova.query.resource.query_handlers

import com.esgi.nova.core_api.resources.queries.FindAllTranslatedResourcesByLanguageQuery
import com.esgi.nova.core_api.resources.views.TranslatedResourceView
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllTranslatedResourcesByLanguageHandler(private val resourceRepository: ResourceRepository) {
    @QueryHandler
    fun handle(query: FindAllTranslatedResourcesByLanguageQuery): List<TranslatedResourceView> {
        return resourceRepository.findAll().map { it.toTranslatedResourceView(query.language) }
    }
}