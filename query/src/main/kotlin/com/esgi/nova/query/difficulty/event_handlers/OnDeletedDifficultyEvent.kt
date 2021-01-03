package com.esgi.nova.query.difficulty.event_handlers

import com.esgi.nova.core_api.difficulty.events.DeletedDifficultyEvent
import com.esgi.nova.query.difficulty.DifficultyRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnDeletedDifficultyEvent constructor(
    private val difficultyRepository: DifficultyRepository
) {
    @EventHandler
    fun on(event: DeletedDifficultyEvent) {
        difficultyRepository.deleteById(event.difficultyId.toUUID())
    }
}