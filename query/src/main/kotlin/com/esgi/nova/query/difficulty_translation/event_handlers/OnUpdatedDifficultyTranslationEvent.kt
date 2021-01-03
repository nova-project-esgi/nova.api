package com.esgi.nova.query.difficulty_translation.event_handlers

import com.esgi.nova.core_api.difficulty.events.UpdatedDifficultyTranslationEvent
import com.esgi.nova.query.difficulty_translation.DifficultyTranslationId
import com.esgi.nova.query.difficulty_translation.DifficultyTranslationRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnUpdatedDifficultyTranslationEvent constructor(private val difficultyTranslationRepository: DifficultyTranslationRepository) {
    @EventHandler
    fun on(event: UpdatedDifficultyTranslationEvent) {
        val translation = difficultyTranslationRepository.getOne(
            DifficultyTranslationId(
                difficultyId = event.difficultyId.toUUID(),
                languageId = event.translationId.toUUID()
            )
        )
        translation.name = event.name
        difficultyTranslationRepository.saveAndFlush(translation)

    }
}