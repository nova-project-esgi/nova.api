package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedResourceNamesByNameAndLanguageCodeQuery(
    val name: String, val code: String,
    override val page: Int,
    override val size: Int
) : IPagination
