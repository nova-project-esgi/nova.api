package com.esgi.nova.core_api.events.commands

import com.esgi.nova.core_api.languages.LanguageIdentifier

data class EventTranslationEditionDto(
    val translationId: LanguageIdentifier,
    val title: String,
    val description: String
)