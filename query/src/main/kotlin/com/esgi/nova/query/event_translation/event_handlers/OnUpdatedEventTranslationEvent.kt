package com.esgi.nova.query.event_translation.event_handlers

import com.esgi.nova.core_api.events.events.UpdatedEventTranslationEvent
import com.esgi.nova.query.event_translation.EventTranslationId
import com.esgi.nova.query.event_translation.EventTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnUpdatedEventTranslationEvent constructor(private val eventTranslationRepository: EventTranslationRepository) {
    @EventHandler
    fun on(event: UpdatedEventTranslationEvent) {
        eventTranslationRepository.findByIdOrNull(
            EventTranslationId(
                eventId = event.eventId.toUUID(),
                languageId = event.translationId.toUUID()
            )
        )?.let { eventTranslation ->
            eventTranslation.title = event.title
            eventTranslation.description = event.description
            eventTranslationRepository.saveAndFlush(eventTranslation)
        }
    }

}