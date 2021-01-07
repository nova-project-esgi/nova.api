package com.esgi.nova.core_api.languages.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedLanguagesByCodeAndSubCodeQuery(
    override val page: Int,
    override val size: Int, val code: String, val subCode: String?

) : IPagination {

}
