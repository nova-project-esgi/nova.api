package com.esgi.nova.ports.provided.dtos.resource_translation

import java.util.*

class ResourceTranslationLanguageIdCmdDto(name: String, resourceId: UUID, val languageId: UUID) :
    ResourceTranslationCmdDto(name, resourceId) {
}