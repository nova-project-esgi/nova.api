package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateChoiceResourceCommand(
    @TargetAggregateIdentifier val choiceId: ChoiceIdentifier,
    val choiceResourceId: ResourceIdentifier,
    val changeValue: Int
)