package com.esgi.nova.core_api.choices.views

import com.esgi.nova.core_api.choice_translations.views.ChoiceTranslationView
import java.util.*

data class DetailedChoiceView(
    val id: UUID,
    val eventId: UUID,
    val translations: List<ChoiceTranslationView>
) {
}