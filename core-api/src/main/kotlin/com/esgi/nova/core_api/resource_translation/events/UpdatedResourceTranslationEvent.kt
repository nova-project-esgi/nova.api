package com.esgi.nova.core_api.resource_translation.events

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resource_translation.commands.ResourceTranslationIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.io.Serializable

data class UpdatedResourceTranslationEvent(
    val resourceId: ResourceIdentifier,
    val translationId: ResourceTranslationIdentifier,
    val name: String
) : Serializable