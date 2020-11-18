package com.esgi.nova.adapters.web.endpoints.users

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/users/{uId}")
data class UserLocationByUUID(val uId: UUID) {
}
