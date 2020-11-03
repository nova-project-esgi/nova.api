package com.esgi.nova.adapters.web.endpoints.game_events

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/game_events/{id}")
data class GameEventLocation(val id: UUID) {
}