package com.esgi.nova.core_api.choices.views

import com.esgi.nova.core_api.resources.views.TranslatedChoiceResourceView
import java.util.*

data class TranslatedChoiceView(
    val id: UUID,
    val eventId: UUID,
    val resources: List<TranslatedChoiceResourceView>,
    val title: String,
    val description: String,
    val language: String
) {
}