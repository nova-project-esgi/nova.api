package com.esgi.nova.core_api.games.commands

import com.esgi.nova.core_api.games.GameIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RemoveGameResourceCommand(
    @TargetAggregateIdentifier val gameId: GameIdentifier,
    val resourceId: ResourceIdentifier
)