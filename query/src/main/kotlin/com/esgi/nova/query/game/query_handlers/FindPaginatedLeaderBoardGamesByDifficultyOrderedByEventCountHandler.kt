package com.esgi.nova.query.game.query_handlers

import com.esgi.nova.core_api.events.queries.FindPaginatedLeaderBoardGamesByDifficultyOrderedByEventCountQuery
import com.esgi.nova.core_api.games.views.LeaderBoardGameView
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.query.extensions.toPageable
import com.esgi.nova.query.extensions.toStaticPage
import com.esgi.nova.query.game.GameRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindPaginatedLeaderBoardGamesByDifficultyOrderedByEventCountHandler(private val gameRepository: GameRepository) {

    @QueryHandler
    fun handle(query: FindPaginatedLeaderBoardGamesByDifficultyOrderedByEventCountQuery): PageBase<LeaderBoardGameView> {
        return gameRepository.findAllByIsEndedAndDifficultyIdOrderByEventsCountDesc(
            isEnded = true,
            pageable = query.toPageable(),
            difficultyId = query.difficultyId.toUUID()
        ).map {
            it.toLeaderBoardGameView()
        }.toStaticPage(query)
    }
}