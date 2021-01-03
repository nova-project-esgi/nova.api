package com.esgi.nova.query.difficulty_ressource.event_handlers

import com.esgi.nova.core_api.resources.events.CreatedResourceDifficultyEvent
import com.esgi.nova.query.difficulty.DifficultyRepository
import com.esgi.nova.query.difficulty_ressource.DifficultyResource
import com.esgi.nova.query.difficulty_ressource.DifficultyResourceId
import com.esgi.nova.query.difficulty_ressource.DifficultyResourceRepository
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedResourceDifficultyEvent constructor(
    private val difficultyResourceRepository: DifficultyResourceRepository,
    private val difficultyRepository: DifficultyRepository,
    private val resourceRepository: ResourceRepository
) {
    @EventHandler
    fun on(event: CreatedResourceDifficultyEvent) {
        val difficulty = difficultyRepository.getOne(event.difficultyId.toUUID())
        val language = resourceRepository.getOne(event.resourceId.toUUID())
        difficultyResourceRepository.saveAndFlush(
            DifficultyResource(
                id = DifficultyResourceId(
                    difficultyId = event.difficultyId.toUUID(),
                    resourceId = event.resourceId.toUUID()
                ), difficulty = difficulty, resource = language, startValue = event.startValue
            )
        )
    }
}

