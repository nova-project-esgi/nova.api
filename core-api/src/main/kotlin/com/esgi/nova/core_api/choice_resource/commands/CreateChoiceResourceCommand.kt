package com.esgi.nova.core_api.choice_resource.commands

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateChoiceResourceCommand(
    @TargetAggregateIdentifier val choiceId: ChoiceIdentifier,
    val choiceResourceId: ResourceIdentifier,
    val changeValue: Int
)