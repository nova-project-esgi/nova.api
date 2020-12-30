package com.esgi.nova.query.event_translation.event_handlers

import com.esgi.nova.core_api.event_translations.events.CreatedEventTranslationEvent
import com.esgi.nova.query.event.EventRepository
import com.esgi.nova.query.event_translation.EventTranslation
import com.esgi.nova.query.event_translation.EventTranslationId
import com.esgi.nova.query.event_translation.EventTranslationRepository
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedEventTranslationEvent constructor(
    private val eventTranslationRepository: EventTranslationRepository,
    private val eventRepository: EventRepository,
    private val languageRepository: LanguageRepository
) {
    @EventHandler
    fun on(event: CreatedEventTranslationEvent) {
        val existingEvent = eventRepository.getOne(event.eventId.toUUID())
        val language = languageRepository.getOne(event.translationId.toUUID())
        eventTranslationRepository.saveAndFlush(
            EventTranslation(
                id = EventTranslationId(
                    eventId = event.eventId.toUUID(),
                    languageId = event.translationId.toUUID()
                ), title = event.title, description = event.description, language = language, event = existingEvent
            )
        )
    }
}


