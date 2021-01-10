package com.esgi.nova.application.services.events.models

import com.esgi.nova.application.pagination.Link
import com.esgi.nova.core_api.choices.views.TranslatedChoiceView
import java.util.*

data class TranslatedEventsWithBackgroundDto(
    val id: UUID,
    val title: String,
    val description: String,
    val language: String,
    val choices: List<TranslatedChoiceView>,
    val backgroundUrl: Link
)

