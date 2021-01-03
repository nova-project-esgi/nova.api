package com.esgi.nova.query.difficulty_translation.event_handlers

import com.esgi.nova.core_api.difficulty.events.DeletedDifficultyTranslationEvent
import com.esgi.nova.query.difficulty_translation.DifficultyTranslationId
import com.esgi.nova.query.difficulty_translation.DifficultyTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnDeletedDifficultyTranslationEvent constructor(private val difficultyTranslationRepository: DifficultyTranslationRepository) {
    @EventHandler
    fun on(event: DeletedDifficultyTranslationEvent) {
        difficultyTranslationRepository.deleteById(
            DifficultyTranslationId(
                difficultyId = event.difficultyId.toUUID(),
                languageId = event.translationId.toUUID()
            )
        )
    }
}