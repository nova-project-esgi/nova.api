package com.esgi.nova.ports.provided.dtos.event_translation

import java.util.*

class EventTranslationLanguageCodesCmdDto(
    title: String,
    description: String,
    var languageCodes: String,
    eventId: UUID
) : EventTranslationCmdDto(title, description, eventId) {
}