package com.esgi.nova.adapters.web.endpoints.resources

import com.esgi.nova.adapters.web.domain.pagination.PaginationDefault
import com.esgi.nova.ports.provided.IPagination
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/resources")
data class ResourcesLocation(
    override val page: Long = PaginationDefault.PAGE,
    override val size: Long = PaginationDefault.SIZE
) : IPagination {
}