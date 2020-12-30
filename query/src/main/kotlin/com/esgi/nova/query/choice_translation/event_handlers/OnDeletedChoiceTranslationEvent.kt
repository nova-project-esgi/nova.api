package com.esgi.nova.query.choice_translation.event_handlers

import com.esgi.nova.core_api.choice_translations.events.DeletedChoiceTranslationEvent
import com.esgi.nova.query.choice_translation.ChoiceTranslationId
import com.esgi.nova.query.choice_translation.ChoiceTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnDeletedChoiceTranslationEvent constructor(private val choiceTranslationRepository: ChoiceTranslationRepository) {
    @EventHandler
    fun on(event: DeletedChoiceTranslationEvent) {
        choiceTranslationRepository.deleteById(
            ChoiceTranslationId(
                choiceId = event.choiceId.toUUID(),
                languageId = event.translationId.toUUID()
            )
        )
    }
}