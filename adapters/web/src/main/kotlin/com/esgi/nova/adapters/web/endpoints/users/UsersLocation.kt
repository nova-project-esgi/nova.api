package com.esgi.nova.adapters.web.endpoints.users

import com.esgi.nova.ports.provided.IPagination
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/users")
data class UsersLocation(override val page: Int = 0, override val size: Int = 10) : IPagination