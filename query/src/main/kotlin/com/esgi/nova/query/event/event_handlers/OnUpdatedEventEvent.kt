package com.esgi.nova.query.event.event_handlers

import com.esgi.nova.core_api.events.events.UpdatedEventEvent
import com.esgi.nova.query.event.EventRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnUpdatedEventEvent constructor(private val eventRepository: EventRepository) {
    @EventHandler
    fun on(event: UpdatedEventEvent) {
        eventRepository.findByIdOrNull(event.eventId.toUUID())?.let { eventForUpdate ->
            eventForUpdate.isActive = event.isActive
            eventForUpdate.isDaily = event.isDaily
            eventRepository.saveAndFlush(eventForUpdate)
        }
    }
}