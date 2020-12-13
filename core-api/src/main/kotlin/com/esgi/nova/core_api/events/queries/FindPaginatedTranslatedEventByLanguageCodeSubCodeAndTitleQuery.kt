package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.pagination.IPagination


data class FindPaginatedTranslatedEventByLanguageCodeSubCodeAndTitleQuery(
        override val page: Int,
        override val size: Int,
        val code: String,
        val subCode: String?,
        val title: String
) : IPagination

