package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteChoiceResourceCommand(
    @TargetAggregateIdentifier val choiceId: ChoiceIdentifier,
    val choiceResourceId: ResourceIdentifier
)