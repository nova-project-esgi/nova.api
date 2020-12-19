package com.esgi.nova.core_api.choice_translations.commands

import com.esgi.nova.core_api.choice_translations.commands.ChoiceTranslationIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateChoiceTranslationCommand(
    @TargetAggregateIdentifier val id: ChoiceTranslationIdentifier,
    val title: String,
    val description: String
)