package com.esgi.nova.query.resource.event_handlers

import com.esgi.nova.core_api.resources.events.CreatedResourceEvent
import com.esgi.nova.query.resource.Resource
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedResourceHandler constructor(private val resourceRepository: ResourceRepository) {
    @EventHandler
    fun on(event: CreatedResourceEvent) {
        resourceRepository.saveAndFlush(
            Resource(id = event.resourceId.toUUID())
        )
    }
}