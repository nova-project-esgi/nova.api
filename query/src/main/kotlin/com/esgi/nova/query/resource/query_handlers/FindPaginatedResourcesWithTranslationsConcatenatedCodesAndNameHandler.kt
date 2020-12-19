package com.esgi.nova.query.resource.query_handlers

import com.esgi.nova.core.domain.StaticPage
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.resources.views.ResourceWithTranslationsView
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.resource.ResourceRepository
import com.esgi.nova.core_api.resources.queries.FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameHandler(private val resourceRepository: ResourceRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery): PageBase<ResourceWithTranslationsView> {
        val res = resourceRepository.findByResourceTranslationsNameStartingWith(query.name)
        return PageBase.emptyPage()
//        return resourceRepository.findAllByResourceTranslationsNameStartingWith(
//            name = query.name,
//            page = query.toPageable()
//        )
//            .map { it.toResourceWithTranslationsView()}
//            .toStaticPage(query)
//        return resourceRepository.findAllByResourceTranslationsNameStartingWithAndResourceTranslationsLanguageConcatenatedCodesStartingWith(
//            concatenatedCode = query.concatenatedCodes,
//            name = query.name,
//            page = query.toPageable()
//        )
//            .map { it.toResourceWithTranslationsView()}
//            .toStaticPage(query)
    }
}