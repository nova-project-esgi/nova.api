package com.esgi.nova.adapters.web.endpoints.translated_resources

import com.esgi.nova.adapters.web.domain.pagination.PaginationDefault
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.ITranslation
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/resources")
data class TranslatedResourcesLocation(
        override val page: Long = PaginationDefault.PAGE,
        override val size: Long = PaginationDefault.SIZE, override var language: String
) : IPagination, ITranslation<String> {
}