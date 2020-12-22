package com.esgi.nova.query.event.event_handlers

import com.esgi.nova.core_api.events.events.DeletedEventEvent
import com.esgi.nova.query.event.EventRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnDeletedEventEvent constructor(private val eventRepository: EventRepository) {
    @EventHandler
    fun on(event: DeletedEventEvent) {
        eventRepository.deleteById(event.eventId.toUUID())
    }
}