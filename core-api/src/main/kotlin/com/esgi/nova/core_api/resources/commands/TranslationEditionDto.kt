package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.resource_translation.commands.ResourceTranslationIdentifier

data class TranslationEditionDto(val resourceId: ResourceIdentifier, val translationId: ResourceTranslationIdentifier, val name: String)