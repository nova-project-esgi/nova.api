package com.esgi.nova.core_api.games.commands

import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class AddGameResourceCommand(
    @TargetAggregateIdentifier val gameId: GameIdentifier,
    val resourceId: ResourceIdentifier,
    val total: Int
)