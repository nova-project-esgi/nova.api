package com.esgi.nova.adapters.web.endpoints.choices

import com.esgi.nova.adapters.web.domain.pagination.PaginationDefault
import com.esgi.nova.ports.provided.IPagination
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/choices")
data class ChoicesLocation(
    override val page: Long = PaginationDefault.PAGE,
    override val size: Long = PaginationDefault.SIZE
) : IPagination {
}