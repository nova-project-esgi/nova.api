package com.esgi.nova.core_api.pagination

abstract class PageBase<T>(elements: Collection<T>) : ArrayList<T>(elements) {
    abstract val pageSize: Int
    abstract val currentPage: Int
    abstract val hasNext: Boolean
    abstract val hasPrevious: Boolean
    abstract val total: Int

    companion object {
        fun <T> emptyPage() = EmptyPage<T>(
            pageSize = 0,
            currentPage = 0,
            hasNext = false,
            hasPrevious = false,
            elements = listOf(),
            total = 0
        )
    }
}
