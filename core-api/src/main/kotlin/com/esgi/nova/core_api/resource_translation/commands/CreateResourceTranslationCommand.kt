package com.esgi.nova.core_api.resource_translation.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateResourceTranslationCommand(
    @TargetAggregateIdentifier val id: ResourceTranslationIdentifier,
    val name: String
)


