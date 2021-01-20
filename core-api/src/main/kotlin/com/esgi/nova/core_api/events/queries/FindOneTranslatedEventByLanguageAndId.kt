package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.events.EventIdentifier

data class FindOneTranslatedEventByLanguageAndId(
    val language: String, val eventId: EventIdentifier
)