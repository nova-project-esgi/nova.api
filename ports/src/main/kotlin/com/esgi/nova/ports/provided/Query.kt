package com.esgi.nova.ports.provided

data class Query(val pagination: IPagination) {
    val offset get() = pagination.size * pagination.page
    val limit get() = offset + pagination.size
}