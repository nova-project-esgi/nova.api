package com.esgi.nova.adapters.web.endpoints.events

import com.esgi.nova.ports.provided.IPagination
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/events")
data class EventsLocation(override val page: Int, override val size: Int) : IPagination {
}

