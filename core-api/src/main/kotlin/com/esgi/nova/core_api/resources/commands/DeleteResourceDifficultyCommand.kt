package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteResourceDifficultyCommand(
    @TargetAggregateIdentifier val resourceId: ResourceIdentifier, val difficultyId: DifficultyIdentifier
)