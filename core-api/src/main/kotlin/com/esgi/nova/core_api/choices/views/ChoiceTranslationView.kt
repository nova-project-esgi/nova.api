package com.esgi.nova.core_api.choices.views

import com.esgi.nova.core_api.languages.queries.views.LanguageView
import java.util.*

data class ChoiceTranslationView(
    val choiceId: UUID,
    val title: String,
    val description: String,
    val language: LanguageView
) {
}