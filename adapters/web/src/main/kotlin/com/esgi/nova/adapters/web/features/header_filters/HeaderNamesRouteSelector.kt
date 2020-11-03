package com.esgi.nova.adapters.web.features.header_filters


import io.ktor.routing.*

class HeaderNamesRouteSelector(private val headerNames: List<String>) :
    RouteSelector(RouteSelectorEvaluation.qualityConstant) {
    override fun evaluate(context: RoutingResolveContext, segmentIndex: Int): RouteSelectorEvaluation {
        headerNames.forEach { header ->
            if (!context.call.request.headers.contains(header)) {
                return RouteSelectorEvaluation.Failed
            }
        }
        return RouteSelectorEvaluation(true, RouteSelectorEvaluation.qualityConstant)
    }

    override fun toString(): String = "(headerNames: ${headerNames.joinToString { it }})"
}