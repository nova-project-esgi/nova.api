package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.languages.LanguageIdentifier


data class ResourceTranslationEditionDto(val resourceId: ResourceIdentifier, val translationId: LanguageIdentifier, val name: String)
