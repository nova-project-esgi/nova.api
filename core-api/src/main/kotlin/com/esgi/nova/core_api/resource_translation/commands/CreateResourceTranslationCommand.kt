package com.esgi.nova.core_api.resource_translation.commands

import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateResourceTranslationCommand(
    @TargetAggregateIdentifier val id: ResourceIdentifier,
    val translationId: ResourceTranslationIdentifier,
    val name: String
)


