package com.esgi.nova.core_api.resource_translation.events

import com.esgi.nova.core_api.resource_translation.commands.ResourceTranslationIdentifier
import java.io.Serializable

data class DeletedResourceTranslationEvent(
    val id: ResourceTranslationIdentifier
) : Serializable