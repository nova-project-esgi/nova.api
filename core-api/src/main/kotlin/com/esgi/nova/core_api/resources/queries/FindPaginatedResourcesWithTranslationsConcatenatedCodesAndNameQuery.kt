package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedResourcesWithTranslationsConcatenatedCodesAndNameQuery(
    val concatenatedCodes: String, val name: String,
    override val page: Int,
    override val size: Int
) : IPagination


