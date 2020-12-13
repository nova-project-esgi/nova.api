package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.pagination.IPagination


data class FindPaginatedEventTitleByLanguageCodeSubCodeAndTitleQuery(
        override val page: Int,
        override val size: Int,
        val code: String,
        val subCode: String?,
        val title: String
) : IPagination
