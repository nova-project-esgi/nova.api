package com.esgi.nova.core_api.difficulty.commands

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.difficulty.dtos.DifficultyTranslationEditionDto
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateDifficultyCommand(
    @TargetAggregateIdentifier val difficultyId: DifficultyIdentifier,
    val translations: List<DifficultyTranslationEditionDto>,
    val isDefault: Boolean,
    val rank: Int
) {
}