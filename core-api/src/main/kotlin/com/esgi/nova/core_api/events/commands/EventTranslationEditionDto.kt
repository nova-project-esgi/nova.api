package com.esgi.nova.core_api.events.commands

import com.esgi.nova.core_api.event_translations.commands.EventTranslationIdentifier

data class EventTranslationEditionDto(
    val translationId: EventTranslationIdentifier,
    val title: String,
    val description: String
)