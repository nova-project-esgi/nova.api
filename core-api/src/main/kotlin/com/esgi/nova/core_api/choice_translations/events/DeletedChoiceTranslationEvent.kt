package com.esgi.nova.core_api.choice_translations.events

import com.esgi.nova.core_api.choice_translations.commands.ChoiceTranslationIdentifier
import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import java.io.Serializable

data class DeletedChoiceTranslationEvent(
    val choiceId: ChoiceIdentifier,
    val translationId: ChoiceTranslationIdentifier
) : Serializable