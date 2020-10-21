package com.esgi.nova.adapters.web.extensions

import com.esgi.nova.adapters.web.authorization.AuthorisedRouteSelector
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.adapters.web.authorization.RoleAuthorization
import io.ktor.application.*
import io.ktor.routing.*

public fun Route.rolesAllowed(vararg roles: Role, build: Route.() -> Unit): Route {
    val authorisedRoute = createChild(AuthorisedRouteSelector(roles.toList()))
    application.feature(RoleAuthorization).interceptPipeline(this, roles.toSet())

    authorisedRoute.build()
    return authorisedRoute
}