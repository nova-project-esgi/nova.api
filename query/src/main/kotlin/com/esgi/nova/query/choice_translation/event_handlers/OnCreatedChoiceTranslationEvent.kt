package com.esgi.nova.query.choice_translation.event_handlers

import com.esgi.nova.core_api.choice_translations.events.CreatedChoiceTranslationEvent
import com.esgi.nova.query.choice.ChoiceRepository
import com.esgi.nova.query.choice_translation.ChoiceTranslation
import com.esgi.nova.query.choice_translation.ChoiceTranslationId
import com.esgi.nova.query.choice_translation.ChoiceTranslationRepository
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnCreatedChoiceTranslationEvent constructor(private val choiceTranslationRepository: ChoiceTranslationRepository, private val choiceRepository: ChoiceRepository, private val languageRepository: LanguageRepository) {
    @EventHandler
    fun on(event: CreatedChoiceTranslationEvent) {
        val choice = choiceRepository.getOne(event.choiceId.toUUID())
        val language = languageRepository.getOne(event.translationId.toUUID())
        choiceTranslationRepository.save(
            ChoiceTranslation(
                id = ChoiceTranslationId(
                    choiceId = event.choiceId.toUUID(),
                    languageId = event.translationId.toUUID()
                ), title = event.title, description = event.description, choice = choice, language = language
            )
        )
    }
}