package com.esgi.nova.adapters.web.features.header_filters

import arrow.core.Either
import arrow.core.right
import io.ktor.application.*

class HeaderNamesFilter {
    internal var filterFunction: suspend ApplicationCall.(Set<String>) -> Either<String, Unit> = { Unit.right() }

    fun filter(body: suspend ApplicationCall.(Set<String>) -> Either<String, Unit>) {
        filterFunction = body
    }
}