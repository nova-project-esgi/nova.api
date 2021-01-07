package com.esgi.nova.core_api.pagination

class EmptyPage<T>(
    override val pageSize: Int,
    override val currentPage: Int,
    override val hasNext: Boolean,
    override val hasPrevious: Boolean, elements: Collection<T>, override val total: Int
) : PageBase<T>(elements) {
}