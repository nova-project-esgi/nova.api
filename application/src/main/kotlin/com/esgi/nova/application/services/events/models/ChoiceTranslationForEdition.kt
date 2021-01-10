package com.esgi.nova.application.services.events.models

import java.util.*

data class ChoiceTranslationForEdition(
    val title: String,
    val description: String,
    val languageId: UUID
)
