package com.esgi.nova.adapters.web.endpoints.games

import com.esgi.nova.ports.provided.IPagination
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/games")
data class GamesLocation(override val page: Int, override val size: Int) : IPagination {
}