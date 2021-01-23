package com.esgi.nova.query.game.query_handlers

import com.esgi.nova.core_api.games.queries.GetAllGameIdsQuery
import com.esgi.nova.query.game.GameRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
open class GetAllGameIdsHandler(private val gameRepository: GameRepository) {

    @QueryHandler
    fun handle(query: GetAllGameIdsQuery): List<UUID> {
        return gameRepository.findAll().map { it.id }
    }
}

