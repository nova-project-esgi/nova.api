package com.esgi.nova.query.language.event_handlers

import com.esgi.nova.core_api.languages.events.LanguageCreatedEvent
import com.esgi.nova.query.language.Language
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component


@Component
open class OnLanguageCreatedEventHandler constructor(private val languageRepository: LanguageRepository) {
    @EventHandler
    fun on(event: LanguageCreatedEvent) {
        languageRepository.saveAndFlush(
            Language(
                event.languageId.toUUID(),
                event.code,
                if (event.subCode.isNullOrBlank()) null else event.subCode
            )
        )
    }
}