package com.esgi.nova.core_api.resource_translation.events

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.springframework.core.io.Resource
import java.io.Serializable

data class CreatedResourceTranslationEvent(
    val resourceId: ResourceIdentifier,
    val translationId: LanguageIdentifier,
    val name: String
) : Serializable

