package com.esgi.nova.application.uses_cases.events.models

import java.util.*

data class ChoiceTranslationForEdition(
    val title: String,
    val description: String,
    val languageId: UUID
)
