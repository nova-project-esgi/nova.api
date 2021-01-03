package com.esgi.nova.core_api.difficulty.commands

import com.esgi.nova.core_api.languages.LanguageIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteDifficultyTranslationCommand(
    @TargetAggregateIdentifier val difficultyId: DifficultyIdentifier, val translationId: LanguageIdentifier
)