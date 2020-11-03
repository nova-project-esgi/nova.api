package com.esgi.nova.adapters.web.extensions

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*

@KtorExperimentalLocationsAPI
suspend fun ApplicationCall.createdIn(location: Any, message: String = "Created") {
    val url = locations.href(location)
    response.headers.append(HttpHeaders.Location, url)
    respondText(message)
}