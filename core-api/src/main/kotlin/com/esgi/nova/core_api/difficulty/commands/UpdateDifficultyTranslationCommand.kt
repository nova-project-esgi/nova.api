package com.esgi.nova.core_api.difficulty.commands

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateDifficultyTranslationCommand(
    @TargetAggregateIdentifier val difficultyId: DifficultyIdentifier,
    val translationId: LanguageIdentifier,
    val name: String
)