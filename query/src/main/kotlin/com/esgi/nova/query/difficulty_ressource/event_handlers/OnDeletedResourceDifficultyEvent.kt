package com.esgi.nova.query.difficulty_ressource.event_handlers

import com.esgi.nova.core_api.resources.events.DeletedResourceDifficultyEvent
import com.esgi.nova.query.difficulty_ressource.DifficultyResourceId
import com.esgi.nova.query.difficulty_ressource.DifficultyResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnDeletedResourceDifficultyEvent constructor(
    private val difficultyResourceRepository: DifficultyResourceRepository
) {
    @EventHandler
    fun on(event: DeletedResourceDifficultyEvent) {
        difficultyResourceRepository.deleteById(
            DifficultyResourceId(
                difficultyId = event.difficultyId.toUUID(),
                resourceId = event.resourceId.toUUID()
            )
        )
    }
}





