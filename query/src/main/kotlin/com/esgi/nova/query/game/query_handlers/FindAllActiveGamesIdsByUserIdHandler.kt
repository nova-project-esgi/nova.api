package com.esgi.nova.query.game.query_handlers

import com.esgi.nova.core_api.games.queries.FindAllActiveGamesIdsByUserIdQuery
import com.esgi.nova.query.game.GameRepository
import com.esgi.nova.query.game_event.GameEventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
open class FindAllActiveGamesIdsByUserIdHandler(private val gameRepository: GameRepository) {

    @QueryHandler
    fun handle(query: FindAllActiveGamesIdsByUserIdQuery): List<UUID> {
        return gameRepository
            .findAllByUserIdAndIsEnded(query.userId.toUUID(), false)
            .map { it.id }
    }
}