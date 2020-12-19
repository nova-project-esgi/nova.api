package com.esgi.nova.core_api.choice_resource.commands

import com.esgi.nova.core_api.choice_translations.commands.ChoiceTranslationIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateChoiceResourceCommand(
    @TargetAggregateIdentifier val id: ChoiceResourceIdentifier,
    val changeValue: Int
)