package com.esgi.nova.adapters.web.domain.pagination

import io.netty.handler.codec.http.HttpMethod

data class Link(private val _rel: Relation, val href: String, val _method: HttpMethod) {
    val rel get() = _rel.name.toLowerCase()
    val method get() = _method.name().toLowerCase()
}