package com.esgi.nova.core_api.events.events

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.events.commands.EventIdentifier
import java.io.Serializable

data class CreatedEventTranslationEvent(
        val eventId: EventIdentifier,
        val languageId: LanguageIdentifier,
        val title: String,
        val description: String
) : Serializable