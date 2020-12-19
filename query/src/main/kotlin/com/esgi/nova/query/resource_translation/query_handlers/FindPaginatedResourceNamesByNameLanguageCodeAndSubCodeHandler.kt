package com.esgi.nova.query.resource_translation.query_handlers

import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resource_translation.queries.FindPaginatedResourceNamesByNameLanguageCodeAndSubCodeQuery
import com.esgi.nova.core_api.resource_translation.views.ResourceTranslationNameView
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.resource_translation.ResourceTranslationRepository
import org.axonframework.queryhandling.QueryHandler

class FindPaginatedResourceNamesByNameLanguageCodeAndSubCodeHandler(private val resourceTranslationRepository: ResourceTranslationRepository) {
    @QueryHandler
    fun handle(query: FindPaginatedResourceNamesByNameLanguageCodeAndSubCodeQuery): PageBase<ResourceTranslationNameView> {
        return resourceTranslationRepository.findAllByNameStartingWithAndLanguageCodeStartingWithAndLanguageSubCodeStartingWith(
            name = query.name,
            code = query.code,
            subCode = query.subCode,
            page = query.toPageable()
        ).map { it.toResourceTranslationNameView() }.toStaticPage(query)
    }
}