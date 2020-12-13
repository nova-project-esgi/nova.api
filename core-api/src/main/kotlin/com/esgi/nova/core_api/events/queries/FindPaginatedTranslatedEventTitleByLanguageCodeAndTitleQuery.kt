package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.pagination.IPagination


data class FindPaginatedTranslatedEventTitleByLanguageCodeAndTitleQuery(
        override val page: Int,
        override val size: Int,
        val code: String,
        val title: String
) : IPagination