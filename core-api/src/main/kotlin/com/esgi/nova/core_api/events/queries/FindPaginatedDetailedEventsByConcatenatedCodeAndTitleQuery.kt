package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedDetailedEventsByConcatenatedCodeAndTitleQuery(
    override val page: Int,
    override val size: Int,
    val concatenatedCode: String,
    val title: String
) : IPagination {
}