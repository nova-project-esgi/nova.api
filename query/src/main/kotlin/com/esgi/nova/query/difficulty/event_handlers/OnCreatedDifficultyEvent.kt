package com.esgi.nova.query.difficulty.event_handlers

import com.esgi.nova.core_api.difficulty.events.CreatedDifficultyEvent
import com.esgi.nova.query.difficulty.Difficulty
import com.esgi.nova.query.difficulty.DifficultyRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedDifficultyEvent constructor(
    private val difficultyRepository: DifficultyRepository
) {
    @EventHandler
    fun on(event: CreatedDifficultyEvent) {
        difficultyRepository.saveAndFlush(
            Difficulty(
                id = event.difficultyId.toUUID(),
                isDefault = event.isDefault,
                rank = event.rank
            )
        )
    }
}