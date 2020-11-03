package com.esgi.nova.ports.provided.dtos.resource_translation

import java.util.*

class ResourceTranslationLanguageCodesCmdDto(name: String, resourceId: UUID, val languageCodes: String) :
    ResourceTranslationCmdDto(
        name,
        resourceId
    ) {
}