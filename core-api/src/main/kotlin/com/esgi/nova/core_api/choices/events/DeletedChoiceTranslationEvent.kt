package com.esgi.nova.core_api.choices.events

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import java.io.Serializable

data class DeletedChoiceTranslationEvent(
    val choiceId: ChoiceIdentifier,
    val translationId: LanguageIdentifier
) : Serializable