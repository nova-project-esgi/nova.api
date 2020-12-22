package com.esgi.nova.query.event.event_handlers

import com.esgi.nova.core_api.events.events.CreatedEventEvent
import com.esgi.nova.query.event.Event
import com.esgi.nova.query.event.EventRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedEventEvent constructor(private val eventRepository: EventRepository) {
    @EventHandler
    fun on(event: CreatedEventEvent) {
        eventRepository.saveAndFlush(Event(id = event.eventId.toUUID(), isActive = event.isActive, isDaily = event.isDaily))
    }
}