package com.esgi.nova.query.game.event_handlers

import com.esgi.nova.core_api.games.events.CreatedGameEvent
import com.esgi.nova.query.difficulty.DifficultyRepository
import com.esgi.nova.query.game.Game
import com.esgi.nova.query.game.GameRepository
import com.esgi.nova.query.user.UserRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedGameEvent constructor(
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository,
    private val difficultyRepository: DifficultyRepository
) {

    @EventHandler
    fun on(event: CreatedGameEvent) {
        val user = userRepository.getOne(event.userId.toUUID())
        val difficulty = difficultyRepository.getOne(event.difficultyId.toUUID())
        gameRepository.saveAndFlush(
            Game(
                id = event.gameId.toUUID(),
                user = user,
                difficulty = difficulty
            )
        )
    }
}




