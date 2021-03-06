package com.esgi.nova.core_api.languages.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedLanguagesByConcatenatedCodeQuery(
    val concatenatedCode: String,
    override val page: Int,
    override val size: Int
) : IPagination {
}