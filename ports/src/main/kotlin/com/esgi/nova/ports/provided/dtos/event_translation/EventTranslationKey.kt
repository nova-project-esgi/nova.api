package com.esgi.nova.ports.provided.dtos.event_translation

import java.util.*

data class EventTranslationKey(val eventId: UUID, val languageId: UUID) {
}