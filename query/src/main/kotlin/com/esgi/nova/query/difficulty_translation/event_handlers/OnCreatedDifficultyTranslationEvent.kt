package com.esgi.nova.query.difficulty_translation.event_handlers

import com.esgi.nova.core_api.difficulty.events.CreatedDifficultyTranslationEvent
import com.esgi.nova.query.difficulty.DifficultyRepository
import com.esgi.nova.query.difficulty_translation.DifficultyTranslation
import com.esgi.nova.query.difficulty_translation.DifficultyTranslationId
import com.esgi.nova.query.difficulty_translation.DifficultyTranslationRepository
import com.esgi.nova.query.language.LanguageRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
open class OnCreatedDifficultyTranslationEvent constructor(
    private val difficultyTranslationRepository: DifficultyTranslationRepository,
    private val difficultyRepository: DifficultyRepository,
    private val languageRepository: LanguageRepository
) {
    @EventHandler
    fun on(event: CreatedDifficultyTranslationEvent) {
        val difficulty = difficultyRepository.getOne(event.difficultyId.toUUID())
        val language = languageRepository.getOne(event.translationId.toUUID())
        difficultyTranslationRepository.saveAndFlush(
            DifficultyTranslation(
                id = DifficultyTranslationId(
                    difficultyId = event.difficultyId.toUUID(),
                    languageId = event.translationId.toUUID()
                ), name = event.name, difficulty = difficulty, language = language
            )
        )
    }
}


