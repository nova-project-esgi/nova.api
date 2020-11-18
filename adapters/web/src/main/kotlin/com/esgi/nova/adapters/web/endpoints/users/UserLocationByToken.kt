package com.esgi.nova.adapters.web.endpoints.users

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/users/{token}")
class UserLocationByToken(val token: String) {
}

