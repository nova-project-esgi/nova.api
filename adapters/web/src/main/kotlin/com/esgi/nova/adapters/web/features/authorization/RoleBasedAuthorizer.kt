package com.esgi.nova.adapters.web.features.authorization

import arrow.core.Either
import arrow.core.right
import com.esgi.nova.ports.provided.enums.Role
import io.ktor.application.*

class RoleBasedAuthorizer {
    internal var authorizationFunction: suspend ApplicationCall.(Set<Role>) -> Either<String, Unit> = { Unit.right() }

    fun validate(body: suspend ApplicationCall.(Set<Role>) -> Either<String, Unit>) {
        authorizationFunction = body
    }
}