package com.esgi.nova.core.domain

import com.esgi.nova.core_api.pagination.PageBase


class StaticPage<T>(override val pageSize: Int, val startPage: Int, override val total: Int, elements: Collection<T>) : PageBase<T>(elements) {
    override val currentPage: Int = startPage
    override val hasNext: Boolean = startPage * pageSize + elements.size < total
    override val hasPrevious: Boolean = startPage > 0

}