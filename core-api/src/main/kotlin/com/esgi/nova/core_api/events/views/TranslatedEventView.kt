package com.esgi.nova.core_api.events.views

import java.util.*

data class TranslatedEventView(
        val id: UUID,
        val languageIds: List<UUID>,
        val title: String,
        val description: String,
        val language: String,
        val choiceIds: List<UUID>)

