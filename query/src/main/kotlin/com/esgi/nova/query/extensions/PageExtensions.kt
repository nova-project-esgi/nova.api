package com.esgi.nova.query.extensions

import com.esgi.nova.core.domain.StaticPage
import com.esgi.nova.core_api.pagination.IPagination
import com.esgi.nova.core_api.pagination.PageBase
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

fun <T> Page<T>.toStaticPage(pagination: IPagination): PageBase<T> = StaticPage(
        pageSize = pagination.size,
        startPage = pagination.page,
        elements = this.toList(),
        total = this.totalElements.toInt()
)


fun IPagination.toPageable(): Pageable = PageRequest.of(this.page, this.size)