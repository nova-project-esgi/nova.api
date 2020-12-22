package com.esgi.nova.query.event_translation.event_handlers

import com.esgi.nova.core_api.event_translations.events.CreatedEventTranslationEvent
import com.esgi.nova.query.event_translation.EventTranslation
import com.esgi.nova.query.event_translation.EventTranslationId
import com.esgi.nova.query.event_translation.EventTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedEventTranslationEvent constructor(private val eventTranslationRepository: EventTranslationRepository) {
    @EventHandler
    fun on(event: CreatedEventTranslationEvent) {

        eventTranslationRepository.save(
            EventTranslation(
                id = EventTranslationId(
                    eventId = event.eventId.toUUID(),
                    languageId = event.translationId.toUUID()
                ), description = event.description, title = event.title
            )
        )
    }
}


