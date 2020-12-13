package com.esgi.nova.core_api.events.views

import java.util.*

class EventTranslationView(
        val eventId: UUID,
        val title: String,
        val description: String,
        val language: String,
        val languageId: UUID
) {
}