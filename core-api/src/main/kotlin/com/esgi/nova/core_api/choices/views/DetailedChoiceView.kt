package com.esgi.nova.core_api.choices.views

import java.util.*

data class DetailedChoiceView(
    val id: UUID,
    val eventId: UUID,
    val translations: List<ChoiceTranslationView>,
    val resources: List<ChoiceResourceView>
) {
}