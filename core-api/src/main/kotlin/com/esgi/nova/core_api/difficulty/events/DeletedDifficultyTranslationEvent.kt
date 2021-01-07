package com.esgi.nova.core_api.difficulty.events

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import java.io.Serializable

data class DeletedDifficultyTranslationEvent(
    val difficultyId: DifficultyIdentifier,
    val translationId: LanguageIdentifier
) : Serializable