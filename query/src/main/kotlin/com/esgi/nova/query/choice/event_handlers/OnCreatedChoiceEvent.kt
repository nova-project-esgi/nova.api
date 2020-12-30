package com.esgi.nova.query.choice.event_handlers

import com.esgi.nova.core_api.choices.events.CreatedChoiceEvent
import com.esgi.nova.query.choice.Choice
import com.esgi.nova.query.choice.ChoiceRepository
import com.esgi.nova.query.event.EventRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnCreatedChoiceEvent constructor(
    private val choiceRepository: ChoiceRepository,
    private val eventRepository: EventRepository
) {

    @EventHandler
    fun on(event: CreatedChoiceEvent) {
        eventRepository.findByIdOrNull(event.eventId.toUUID())?.let { eventEntity ->
            choiceRepository.saveAndFlush(Choice(id = event.choiceId.toUUID(), event = eventEntity))
        }
    }
}

