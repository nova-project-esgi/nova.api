package com.esgi.nova.query.resource_translation.query_handlers

import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resources.queries.FindPaginatedResourceTranslationNameViewsByNameAndConcatenatedCodeQuery
import com.esgi.nova.core_api.resources.views.ResourceTranslationNameView
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.resource_translation.ResourceTranslationRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedResourceByNameAndConcatenatedCodeHandler(private val resourceTranslationRepository: ResourceTranslationRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedResourceTranslationNameViewsByNameAndConcatenatedCodeQuery): PageBase<ResourceTranslationNameView> {
        return resourceTranslationRepository.findAllByNameStartingWithAndLanguageConcatenatedCodesStartingWith(
            name = query.name,
            concatenatedCode = query.concatenatedCode,
            page = query.toPageable()
        ).map { it.toResourceTranslationNameView() }.toStaticPage(query)
    }
}

