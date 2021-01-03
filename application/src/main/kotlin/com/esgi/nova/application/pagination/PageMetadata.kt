package com.esgi.nova.application.pagination

import com.esgi.nova.core_api.pagination.PageBase
import io.netty.handler.codec.http.HttpMethod
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder

class PageMetadata<T>(pageBase: PageBase<T>, url: String = ServletUriComponentsBuilder.fromCurrentRequest().toUriString()) {

    val links = mutableListOf<Link>()
    val values: PageBase<T> = pageBase
    val total: Int = pageBase.total
    init {
        links += Link(Relation.CURRENT, getUrl(pageBase, 0, url), HttpMethod.GET)
        if (pageBase.hasNext) {
            links += Link(Relation.NEXT, getUrl(pageBase, 1, url), HttpMethod.GET)
        }
        if (pageBase.hasPrevious) {
            links += Link(Relation.PREVIOUS, getUrl(pageBase, -1, url), HttpMethod.GET)
        }
    }

    private fun getUrl(pageBase: PageBase<T>, contiguousPage: Int, url: String) =
        UriComponentsBuilder.fromHttpUrl(url).also { urlBuilder ->
            urlBuilder
                .replaceQueryParam("size","${pageBase.pageSize}" )
                .replaceQueryParam("page", "${pageBase.currentPage + contiguousPage}")
        }.build().toUriString()
}
