package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateResourceTranslationCommand(
    @TargetAggregateIdentifier val resourceId: ResourceIdentifier,
    val translationId: LanguageIdentifier,
    val name: String
)

