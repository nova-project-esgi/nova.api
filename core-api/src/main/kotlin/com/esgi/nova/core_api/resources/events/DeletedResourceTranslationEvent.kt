package com.esgi.nova.core_api.resources.events

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import java.io.Serializable

data class DeletedResourceTranslationEvent(
    val resourceId: ResourceIdentifier,
    val translationId: LanguageIdentifier
) : Serializable

