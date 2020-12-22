package com.esgi.nova.core_api.events.views

import com.esgi.nova.core_api.choices.views.DetailedChoiceView
import java.util.*

data class DetailedEventView(
    val id: UUID,
    val translations: List<EventTranslationView>,
    val choices: List<DetailedChoiceView>
)