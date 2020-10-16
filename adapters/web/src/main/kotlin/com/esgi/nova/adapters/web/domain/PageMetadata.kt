package com.esgi.nova.adapters.web.domain

import io.ktor.http.*
import io.netty.handler.codec.http.HttpMethod

class PageMetadata<T>(page: Page<T>, url: String) {

    val links = mutableListOf<Link>()
    val values: List<T> = page.currentPageElements

    init {
        links += Link(Relation.CURRENT, getUrl(page, 0, url), HttpMethod.GET)
        if (page.hasNext) {
            links += Link(Relation.NEXT, getUrl(page, 1, url), HttpMethod.GET)
        }
        if (page.hasPrevious) {
            links += Link(Relation.PREVIOUS, getUrl(page, -1, url), HttpMethod.GET)
        }
    }

    private fun getUrl(page: Page<T>, contiguousPage: Int, url: String) =
        URLBuilder(url).also { urlBuilder ->
            urlBuilder.parameters.also { parameterBuilder ->
                parameterBuilder.append("size", "${page.pageSize}")
                parameterBuilder.append("page", "${page.currentPage + contiguousPage}")
            }
        }.buildString()
}
