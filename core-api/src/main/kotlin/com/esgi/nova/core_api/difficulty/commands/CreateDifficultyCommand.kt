package com.esgi.nova.core_api.difficulty.commands

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateDifficultyCommand(
    @TargetAggregateIdentifier val difficultyId: DifficultyIdentifier,
    val isDefault: Boolean,
    val rank: Int
) {
}