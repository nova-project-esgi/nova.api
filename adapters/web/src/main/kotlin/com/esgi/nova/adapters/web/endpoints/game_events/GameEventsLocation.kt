package com.esgi.nova.adapters.web.endpoints.game_events

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/games/{gID}/events/{eID}")
data class GameEventsLocation(val gID: UUID, val eID: UUID) {
}