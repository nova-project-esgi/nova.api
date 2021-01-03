package com.esgi.nova.query.game.query_handlers

import com.esgi.nova.core_api.games.queries.FindOneGameViewByIdQuery
import com.esgi.nova.core_api.games.views.GameView
import com.esgi.nova.query.game.GameRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class FindOneGameViewBiIdHandler(private val gameRepository: GameRepository) {

    @QueryHandler
    fun handle(query: FindOneGameViewByIdQuery): GameView?{
        return gameRepository.findByIdOrNull(query.id.toUUID())?.toGameView()
    }
}

