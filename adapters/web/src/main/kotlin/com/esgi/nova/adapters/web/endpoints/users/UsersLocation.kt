package com.esgi.nova.adapters.web.endpoints.users

import com.esgi.nova.ports.provided.IPagination
import io.ktor.locations.*

@KtorExperimentalLocationsAPI
@Location("/users")
data class UsersLocation(override val page: Long = 0, override val size: Long = 10) : IPagination