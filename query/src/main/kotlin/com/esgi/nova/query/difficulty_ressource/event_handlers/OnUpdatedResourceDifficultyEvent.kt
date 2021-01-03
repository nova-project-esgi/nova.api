package com.esgi.nova.query.difficulty_ressource.event_handlers

import com.esgi.nova.core_api.resources.events.UpdatedResourceDifficultyEvent
import com.esgi.nova.query.difficulty_ressource.DifficultyResourceId
import com.esgi.nova.query.difficulty_ressource.DifficultyResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnUpdatedResourceDifficultyEvent constructor(
    private val difficultyResourceRepository: DifficultyResourceRepository
) {
    @EventHandler
    fun on(event: UpdatedResourceDifficultyEvent) {
        val resourceDifficulty = difficultyResourceRepository.getOne(
            DifficultyResourceId(
                resourceId = event.resourceId.toUUID(),
                difficultyId = event.difficultyId.toUUID()
            )
        )
        resourceDifficulty.startValue = event.startValue
        difficultyResourceRepository.saveAndFlush(resourceDifficulty)
    }
}