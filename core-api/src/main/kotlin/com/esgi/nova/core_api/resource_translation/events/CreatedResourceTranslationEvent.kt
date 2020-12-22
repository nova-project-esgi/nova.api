package com.esgi.nova.core_api.resource_translation.events

import com.esgi.nova.core_api.resource_translation.commands.ResourceTranslationIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.springframework.core.io.Resource
import java.io.Serializable

data class CreatedResourceTranslationEvent(
    val resourceId: ResourceIdentifier,
    val translationId: ResourceTranslationIdentifier,
    val name: String
) : Serializable

