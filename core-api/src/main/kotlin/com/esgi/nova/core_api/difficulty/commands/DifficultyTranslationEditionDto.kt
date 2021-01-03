package com.esgi.nova.core_api.difficulty.commands

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier

data class DifficultyTranslationEditionDto(val difficultyId: DifficultyIdentifier, val translationId: LanguageIdentifier, val name: String)