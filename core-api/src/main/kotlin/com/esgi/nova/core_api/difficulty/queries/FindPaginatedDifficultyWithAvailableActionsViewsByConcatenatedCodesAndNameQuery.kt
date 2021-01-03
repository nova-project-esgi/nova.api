package com.esgi.nova.core_api.difficulty.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedDifficultyWithAvailableActionsViewsByConcatenatedCodesAndNameQuery(
    val concatenatedCodes: String, val name: String,
    override val page: Int,
    override val size: Int
) : IPagination

