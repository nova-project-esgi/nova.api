package com.esgi.nova.core_api.resources.events

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.io.Serializable

data class DeletedResourceTranslationEvent(
    val resourceId: ResourceIdentifier,
    val languageId: LanguageIdentifier
) : Serializable