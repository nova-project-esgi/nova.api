package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedTranslatedResourcesByConcatenatedCodeAndNameQuery(
    override val page: Int,
    override val size: Int,
    val concatenatedCode: String,
    val name: String
) : IPagination {
}