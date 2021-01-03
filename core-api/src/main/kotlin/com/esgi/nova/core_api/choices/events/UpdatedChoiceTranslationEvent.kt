package com.esgi.nova.core_api.choices.events

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import java.io.Serializable

data class UpdatedChoiceTranslationEvent(
    val choiceId: ChoiceIdentifier,
    val translationId: LanguageIdentifier,
    val title: String,
    val description: String
) : Serializable