package com.esgi.nova.domain.domain

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.ITotalCollection

class StaticPage<T> : IPage<T> {
    override val pageSize: Int
    override val currentPage: Int
    override val hasNext: Boolean
    override val hasPrevious: Boolean

    constructor(pageSize: Int, startPage: Int, elements: ITotalCollection<T>) : super(elements) {
        this.pageSize = pageSize
        this.hasNext = startPage * pageSize + elements.size < elements.total
        this.hasPrevious = startPage > 0
        this.currentPage = startPage
    }

    constructor(pageSize: Int, startPage: Int, total: Int, elements: Collection<T>) : super(elements) {
        this.pageSize = pageSize
        this.hasNext = startPage * pageSize + elements.size < total
        this.hasPrevious = startPage > 0
        this.currentPage = startPage
    }

    companion object {
        fun <T> emptyPage(pagination: IPagination) = StaticPage<T>(
            pagination.page.toInt(),
            pagination.size.toInt(), EmptyTotalCollection()
        )

    }
}