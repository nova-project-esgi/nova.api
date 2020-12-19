package com.esgi.nova.core_api.choice_translations.views

import java.util.*

data class ChoiceTranslationView(
        val eventId: UUID,
        val choiceId: UUID,
        val resourceIds: List<UUID>,
        val title: String,
        val description: String,
        val language: String
) {
}