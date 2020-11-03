package com.esgi.nova.adapters.web.extensions

import com.esgi.nova.adapters.web.features.authorization.AuthorisedRouteSelector
import com.esgi.nova.adapters.web.features.authorization.RoleAuthorization
import com.esgi.nova.adapters.web.features.header_filters.ExcludedHeaderNamesRouteSelector
import com.esgi.nova.adapters.web.features.header_filters.HeaderNamesRouteSelector
import com.esgi.nova.ports.provided.enums.Role
import io.ktor.application.*
import io.ktor.routing.*

public fun Route.rolesAllowed(vararg roles: Role, build: Route.() -> Unit): Route {
    val authorisedRoute = createChild(AuthorisedRouteSelector(roles.toList()))
    application.feature(RoleAuthorization).interceptPipeline(this, roles.toSet())

    authorisedRoute.build()
    return authorisedRoute
}

public fun Route.withHeaderNames(vararg headerNames: String, build: Route.() -> Unit): Route {
    val selector = HeaderNamesRouteSelector(headerNames.toList())
    return createChild(selector).apply(build)
}

public fun Route.exceptHeaderNames(vararg excludedHeaderNames: String, build: Route.() -> Unit): Route {
    val selector = ExcludedHeaderNamesRouteSelector(excludedHeaderNames.toList())
    return createChild(selector).apply(build)
}
