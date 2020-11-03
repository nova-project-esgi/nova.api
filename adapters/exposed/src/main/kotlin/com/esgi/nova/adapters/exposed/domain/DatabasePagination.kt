package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.ports.provided.IPagination

class DatabasePagination : IPagination {
    val offset get() = page * size
    override val page: Long
    override val size: Long

    constructor(pagination: IPagination) {
        page = pagination.page
        size = pagination.size
    }

    constructor(page: Long, size: Long) {
        this.page = page
        this.size = size
    }
}