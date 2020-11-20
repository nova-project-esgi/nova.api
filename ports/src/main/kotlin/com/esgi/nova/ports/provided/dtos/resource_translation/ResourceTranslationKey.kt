package com.esgi.nova.ports.provided.dtos.resource_translation

import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.dtos.ITranslationEntityKey
import java.util.*

data class ResourceTranslationKey<T>(override val entityId: UUID, override var language: T): ITranslationEntityKey<UUID,T> {
}