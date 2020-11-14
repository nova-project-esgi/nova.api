package com.esgi.nova.ports.provided.dtos.event_translation

import com.esgi.nova.ports.provided.dtos.ITranslation
import java.util.*

data class EventTranslationKey<T>(val eventId: UUID, override var language: T): ITranslation<T> {
}