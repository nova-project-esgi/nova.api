package com.esgi.nova.query.choice_translation.event_handlers

import com.esgi.nova.core_api.choice_translations.events.UpdatedChoiceTranslationEvent
import com.esgi.nova.query.choice_translation.ChoiceTranslationId
import com.esgi.nova.query.choice_translation.ChoiceTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
open class OnUpdatedChoiceTranslationEvent constructor(private val choiceTranslationRepository: ChoiceTranslationRepository) {
    @EventHandler
    fun on(event: UpdatedChoiceTranslationEvent) {
        choiceTranslationRepository.findByIdOrNull(
            ChoiceTranslationId(
                choiceId = event.choiceId.toUUID(),
                languageId = event.translationId.toUUID()
            )
        )?.let { choice ->
            choice.description = event.description
            choice.title = event.title
            choiceTranslationRepository.saveAndFlush(choice)
        }
    }
}