package com.esgi.nova.adapters.web.endpoints.event_translations

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/languages/{codes}/events")
class EventTranslationsLocation(val codes: String) {
}