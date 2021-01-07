package com.esgi.nova.core_api.events.events

import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import java.io.Serializable

data class DeletedEventTranslationEvent(
    val eventId: EventIdentifier,
    val translationId: LanguageIdentifier
) : Serializable