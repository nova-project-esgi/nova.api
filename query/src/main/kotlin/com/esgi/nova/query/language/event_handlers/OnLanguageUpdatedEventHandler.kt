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
        languageRepository.findByIdOrNull(event.languageId.toUUID())?.let { language ->
            language.code = event.code
            language.subCode = if (event.subCode.isNullOrBlank()) null else event.subCode
            language.isDefault = event.isDefault
            languageRepository.saveAndFlush(language)
        }
    }
}

