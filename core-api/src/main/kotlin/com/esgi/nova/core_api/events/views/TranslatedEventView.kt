package com.esgi.nova.core_api.events.views

import com.esgi.nova.core_api.choices.views.TranslatedChoiceView
import java.util.*

data class TranslatedEventView(
        val id: UUID,
        val title: String,
        val description: String,
        val language: String,
        val choices: List<TranslatedChoiceView>)

