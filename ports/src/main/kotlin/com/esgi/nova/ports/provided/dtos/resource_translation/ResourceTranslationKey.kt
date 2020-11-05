package com.esgi.nova.ports.provided.dtos.resource_translation

import java.util.*

data class ResourceTranslationKey<T>(val resourceId: UUID, val language: T) {
}