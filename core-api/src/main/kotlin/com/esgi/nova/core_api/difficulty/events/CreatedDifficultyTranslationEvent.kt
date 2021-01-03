package com.esgi.nova.core_api.difficulty.events

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import java.io.Serializable

data class CreatedDifficultyTranslationEvent(
    val difficultyId: DifficultyIdentifier,
    val translationId: LanguageIdentifier,
    val name: String
) : Serializable