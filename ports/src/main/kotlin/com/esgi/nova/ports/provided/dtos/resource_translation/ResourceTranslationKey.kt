package com.esgi.nova.ports.provided.dtos.resource_translation

import com.esgi.nova.ports.provided.dtos.ITranslation
import java.util.*

data class ResourceTranslationKey<T>(val resourceId: UUID, override var language: T): ITranslation<T> {
}