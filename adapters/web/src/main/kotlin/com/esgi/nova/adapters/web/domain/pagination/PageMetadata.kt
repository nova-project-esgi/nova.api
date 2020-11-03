package com.esgi.nova.adapters.web.domain.pagination

import com.esgi.nova.ports.provided.IPage
import io.ktor.http.*
import io.netty.handler.codec.http.HttpMethod

class PageMetadata<T>(page: IPage<T>, url: String) {

    val links = mutableListOf<Link>()
    val values: IPage<T> = page

    init {
        links += Link(Relation.CURRENT, getUrl(page, 0, url), HttpMethod.GET)
        if (page.hasNext) {
            links += Link(Relation.NEXT, getUrl(page, 1, url), HttpMethod.GET)
        }
        if (page.hasPrevious) {
            links += Link(Relation.PREVIOUS, getUrl(page, -1, url), HttpMethod.GET)
        }
    }

    private fun getUrl(page: IPage<T>, contiguousPage: Int, url: String) =
        URLBuilder(url).also { urlBuilder ->
            urlBuilder.parameters.also { parameterBuilder ->
                parameterBuilder["size"] = "${page.pageSize}"
                parameterBuilder["page"] = "${page.currentPage + contiguousPage}"
            }
        }.buildString()
}
