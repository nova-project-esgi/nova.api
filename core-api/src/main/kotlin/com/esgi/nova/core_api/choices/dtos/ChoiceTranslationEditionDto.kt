package com.esgi.nova.core_api.choices.dtos

import com.esgi.nova.core_api.languages.LanguageIdentifier

data class ChoiceTranslationEditionDto(
    val translationId: LanguageIdentifier,
    val title: String,
    val description: String
)

