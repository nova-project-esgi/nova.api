package com.esgi.nova.core_api.languages.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedLanguagesWithAvailableActionsByCodeQuery(
    override val page: Int,
    override val size: Int,
    val code: String
) :
    IPagination