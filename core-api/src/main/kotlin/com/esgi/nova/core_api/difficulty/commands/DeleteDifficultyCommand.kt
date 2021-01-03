package com.esgi.nova.core_api.difficulty.commands

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteDifficultyCommand(@TargetAggregateIdentifier val difficultyId: DifficultyIdentifier) {
}