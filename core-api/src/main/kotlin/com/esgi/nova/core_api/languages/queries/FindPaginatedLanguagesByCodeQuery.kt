package com.esgi.nova.core_api.languages.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedLanguagesByCodeQuery(override val page: Int, override val size: Int, val code: String) :
        IPagination