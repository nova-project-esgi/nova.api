package com.esgi.nova.query.language.event_handlers

import com.esgi.nova.core_api.languages.events.LanguageUpdateEvent
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnLanguageUpdatedEventHandler constructor(private val languageRepository: LanguageRepository) {
    @EventHandler
    fun on(event: LanguageUpdateEvent) {
        languageRepository.findByIdOrNull(event.id.toUUID())?.let { language ->
            language.code = event.code
            language.subCode = event.subCode
            languageRepository.saveAndFlush(language)
        }
    }
}

