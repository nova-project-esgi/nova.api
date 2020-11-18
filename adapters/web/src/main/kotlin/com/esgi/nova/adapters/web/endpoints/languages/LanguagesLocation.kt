package com.esgi.nova.adapters.web.endpoints.languages

import com.esgi.nova.adapters.web.domain.pagination.PaginationDefault
import com.esgi.nova.ports.provided.IPagination
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/languages")
data class LanguagesLocation(
    override val page: Long = PaginationDefault.PAGE,
    override val size: Long = PaginationDefault.SIZE,
    val codes: String? = null,
) : IPagination {
}