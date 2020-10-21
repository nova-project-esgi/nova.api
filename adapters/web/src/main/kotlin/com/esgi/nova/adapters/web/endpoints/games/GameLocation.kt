package com.esgi.nova.adapters.web.endpoints.games

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/games/{id}")
data class GameLocation(val id: UUID)