package com.esgi.nova.ports.provided.dtos.event_translation

import java.util.*

data class EventTranslationKey<T>(val eventId: UUID, val language: T) {
}