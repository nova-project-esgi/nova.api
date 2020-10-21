package com.esgi.nova.adapters.web.endpoints.events

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/events/{id}")
data class EventLocation(val id: UUID)