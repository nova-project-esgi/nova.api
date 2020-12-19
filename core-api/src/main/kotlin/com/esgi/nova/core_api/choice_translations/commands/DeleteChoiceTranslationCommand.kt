package com.esgi.nova.core_api.choice_translations.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteChoiceTranslationCommand(
    @TargetAggregateIdentifier val id: ChoiceTranslationIdentifier
)