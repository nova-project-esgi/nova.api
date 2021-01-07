package com.esgi.nova.core_api.events.views

import com.esgi.nova.core_api.languages.views.LanguageView
import java.util.*

class EventTranslationView(
    val eventId: UUID,
    val title: String,
    val description: String,
    val language: LanguageView
) {
}