package com.esgi.nova.adapters.web.endpoints.events

import com.esgi.nova.adapters.web.domain.pagination.PaginationDefault
import com.esgi.nova.ports.provided.IPagination
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/events")
data class EventsLocation(
    override val page: Long = PaginationDefault.PAGE,
    override val size: Long = PaginationDefault.SIZE
) : IPagination {
}

