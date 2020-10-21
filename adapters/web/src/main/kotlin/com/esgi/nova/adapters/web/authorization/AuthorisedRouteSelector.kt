package com.esgi.nova.adapters.web.authorization

import com.esgi.nova.ports.provided.enums.Role
import io.ktor.routing.*

class AuthorisedRouteSelector(private val roles: List<Role>) : RouteSelector(RouteSelectorEvaluation.qualityConstant) {
    override fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation =
        RouteSelectorEvaluation.Constant
    override fun toString(): String = "(authorize ${roles.joinToString { it.name }})"
}