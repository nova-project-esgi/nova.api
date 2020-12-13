package com.esgi.nova.core_api.choices.views

import java.util.*

data class TranslatedChoiceView(
        val id: UUID,
        val eventId: UUID,
        val translationIds: List<UUID>,
        val resourceIds: List<UUID>,
        val title: String,
        val description: String,
        val language: String
) {
}