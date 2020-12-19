package com.esgi.nova.core_api.choice_translations.events

import com.esgi.nova.core_api.choice_translations.commands.ChoiceTranslationIdentifier
import java.io.Serializable

data class DeletedChoiceTranslationEvent(
    val id: ChoiceTranslationIdentifier
) : Serializable