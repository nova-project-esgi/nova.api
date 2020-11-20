package com.esgi.nova.ports.provided.dtos.event_translation

import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.dtos.ITranslationEntityKey
import java.util.*

data class EventTranslationKey<T>(override val entityId: UUID, override var language: T): ITranslationEntityKey<UUID, T> {
}