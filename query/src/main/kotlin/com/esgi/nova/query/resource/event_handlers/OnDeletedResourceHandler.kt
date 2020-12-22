package com.esgi.nova.query.resource.event_handlers

import com.esgi.nova.core_api.resources.events.DeletedResourceEvent
import com.esgi.nova.query.resource.ResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnDeletedResourceHandler constructor(
    private val resourceRepository: ResourceRepository
) {
    @EventHandler
    fun on(event: DeletedResourceEvent) {
        resourceRepository.deleteById(event.resourceId.toUUID())
    }
}