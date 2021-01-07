package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.resources.ResourceIdentifier
import com.esgi.nova.core_api.resources.dtos.ResourceDifficultyEditionDto
import com.esgi.nova.core_api.resources.dtos.ResourceTranslationEditionDto
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateResourceCommand(
    @TargetAggregateIdentifier val resourceId: ResourceIdentifier,
    val translations: List<ResourceTranslationEditionDto>,
    val difficulties: List<ResourceDifficultyEditionDto>
) {
}

