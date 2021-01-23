package com.esgi.nova.query.game.query_handlers

import com.esgi.nova.core_api.games.queries.FindLastActiveGameResumeByUserIdQuery
import com.esgi.nova.core_api.games.views.GameStateView
import com.esgi.nova.query.game.GameRepository
import com.esgi.nova.query.game_event.GameEventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindLastActiveGameResumeByUserIdHandler(private val gameRepository: GameRepository) {

    @QueryHandler
    fun handle(query: FindLastActiveGameResumeByUserIdQuery): GameStateView? {
        return gameRepository
            .findAllByUserIdAndIsEnded(query.userId.toUUID(), false)
            .firstOrNull()?.toGameStateView()
    }
}


