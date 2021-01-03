package com.esgi.nova.query.difficulty.event_handlers

import com.esgi.nova.core_api.difficulty.events.UpdatedDifficultyEvent
import com.esgi.nova.query.difficulty.DifficultyRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnUpdatedDifficultyEvent constructor(
    private val difficultyRepository: DifficultyRepository
) {
    @EventHandler
    fun on(event: UpdatedDifficultyEvent) {
        val difficulty = difficultyRepository.getOne(event.difficultyId.toUUID()).apply {
            isDefault = event.isDefault;
            rank = event.rank
        }
        difficultyRepository.saveAndFlush(
            difficulty
        )
    }
}

