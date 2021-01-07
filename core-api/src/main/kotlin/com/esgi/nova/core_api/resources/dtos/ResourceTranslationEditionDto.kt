package com.esgi.nova.core_api.resources.dtos

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier


data class ResourceTranslationEditionDto(
    val resourceId: ResourceIdentifier,
    val translationId: LanguageIdentifier,
    val name: String
)
