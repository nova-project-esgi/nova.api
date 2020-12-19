package com.esgi.nova.query.resource.event_handlers

import com.esgi.nova.core_api.events.events.CreatedEventTranslationEvent
import com.esgi.nova.core_api.resources.events.CreatedResourceEvent
import com.esgi.nova.query.event_translation.EventTranslation
import com.esgi.nova.query.event_translation.EventTranslationId
import com.esgi.nova.query.resource.Resource
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedResourceHandler constructor(private val resourceRepository: ResourceRepository) {
    @EventHandler
    fun on(event: CreatedResourceEvent) {
        resourceRepository.saveAndFlush(
            Resource(id = event.id.toUUID())
        )
    }
}