package com.esgi.nova.adapters.web.endpoints.event_translations

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/events/{eId}/languages/{codes}")
data class EventTranslationLocation(val eId: UUID, val codes: String) {
}