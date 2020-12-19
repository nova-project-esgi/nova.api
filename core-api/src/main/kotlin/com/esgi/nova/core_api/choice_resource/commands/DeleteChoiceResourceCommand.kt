package com.esgi.nova.core_api.choice_resource.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteChoiceResourceCommand(
    @TargetAggregateIdentifier val id: ChoiceResourceIdentifier
)