package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateResourceDifficultyCommand(
    @TargetAggregateIdentifier val resourceId: ResourceIdentifier,
    val difficultyId: DifficultyIdentifier,
    val startValue: Int
)

