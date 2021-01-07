package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteChoiceTranslationCommand(
    @TargetAggregateIdentifier val choiceId: ChoiceIdentifier,
    val translationId: LanguageIdentifier
)