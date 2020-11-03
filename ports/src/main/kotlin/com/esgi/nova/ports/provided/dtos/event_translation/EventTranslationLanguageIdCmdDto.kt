package com.esgi.nova.ports.provided.dtos.event_translation

import java.util.*

class EventTranslationLanguageIdCmdDto(
    title: String,
    description: String,
    var languageId: UUID,
    eventId: UUID
) : EventTranslationCmdDto(title, description, eventId) {
}