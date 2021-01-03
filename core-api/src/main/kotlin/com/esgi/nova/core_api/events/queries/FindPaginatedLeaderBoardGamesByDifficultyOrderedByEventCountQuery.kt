package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.pagination.IPagination

data class FindPaginatedLeaderBoardGamesByDifficultyOrderedByEventCountQuery(override val page: Int, override val size: Int, val difficultyId: DifficultyIdentifier) :
    IPagination