package com.esgi.nova.core_api.user.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedUserAdminEditsByUsernameQuery(
    val username: String,
    override val page: Int,
    override val size: Int
) : IPagination
