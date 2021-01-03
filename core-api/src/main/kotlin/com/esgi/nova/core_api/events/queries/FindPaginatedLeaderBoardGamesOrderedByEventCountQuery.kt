package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedLeaderBoardGamesOrderedByEventCountQuery(override val page: Int, override val size: Int) :
    IPagination

