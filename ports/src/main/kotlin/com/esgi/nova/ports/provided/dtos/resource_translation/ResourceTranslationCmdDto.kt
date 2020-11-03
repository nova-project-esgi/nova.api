package com.esgi.nova.ports.provided.dtos.resource_translation

import java.util.*

open class ResourceTranslationCmdDto(override var name: String, var resourceId: UUID) : IResourceTranslation {
}