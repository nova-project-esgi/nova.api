package com.esgi.nova.query.language.event_handlers

import com.esgi.nova.core_api.languages.events.LanguageDeletedEvent
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnLanguageDeletedEvent constructor(private val languageRepository: LanguageRepository) {
    @EventHandler
    fun on(event: LanguageDeletedEvent) {
        languageRepository.deleteById(event.languageId.toUUID());
    }
}