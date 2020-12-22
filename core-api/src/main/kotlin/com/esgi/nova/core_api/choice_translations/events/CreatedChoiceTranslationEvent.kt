package com.esgi.nova.core_api.choice_translations.events

import com.esgi.nova.core_api.choice_translations.commands.ChoiceTranslationIdentifier
import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.io.Serializable

data class CreatedChoiceTranslationEvent(
    @TargetAggregateIdentifier val choiceId: ChoiceIdentifier,
    val translationId: ChoiceTranslationIdentifier,
    val title: String,
    val description: String
) : Serializable