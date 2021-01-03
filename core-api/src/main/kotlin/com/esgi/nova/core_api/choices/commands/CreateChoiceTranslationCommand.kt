package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateChoiceTranslationCommand(
    @TargetAggregateIdentifier val choiceId: ChoiceIdentifier,
    val translationId: LanguageIdentifier,
    val title: String,
    val description: String
)