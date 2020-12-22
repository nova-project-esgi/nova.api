package com.esgi.nova.core_api.choice_translations.commands

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteChoiceTranslationCommand(
    @TargetAggregateIdentifier  val choiceId: ChoiceIdentifier,
    val translationId: ChoiceTranslationIdentifier
)