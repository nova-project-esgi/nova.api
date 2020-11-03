package com.esgi.nova.ports.provided

data class Query(val pagination: IPagination) {
    val offset get(): Long = pagination.size * pagination.page
    val limit get(): Long = offset + pagination.size
}