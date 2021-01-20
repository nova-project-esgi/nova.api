package com.esgi.nova.query.game.query_handlers

import com.esgi.nova.core_api.games.queries.FindLastActiveGameResumeByUserIdQuery
import com.esgi.nova.core_api.games.views.GameStateView
import com.esgi.nova.query.game_event.GameEventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindLastActiveGameResumeByUserIdHandler(private val gameEventRepository: GameEventRepository) {

    @QueryHandler
    fun handle(query: FindLastActiveGameResumeByUserIdQuery): GameStateView? {
        return gameEventRepository.findFirstByGameUserIdOrderByLinkTimeDesc(query.userId.toUUID())?.game?.toGameStateView()
    }
}


