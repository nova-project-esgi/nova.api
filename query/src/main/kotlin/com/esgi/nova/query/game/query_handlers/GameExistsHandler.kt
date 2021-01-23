package com.esgi.nova.query.game.query_handlers

import com.esgi.nova.core_api.games.queries.GameExistsQuery
import com.esgi.nova.query.extensions.findNullableById
import com.esgi.nova.query.game.GameRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class GameExistsHandler(private val gameRepository: GameRepository) {

    @QueryHandler
    fun handle(query: GameExistsQuery): Boolean {
        return gameRepository.findNullableById(query.id.toUUID()) != null
    }
}