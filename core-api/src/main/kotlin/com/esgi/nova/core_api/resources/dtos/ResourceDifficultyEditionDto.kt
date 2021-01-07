package com.esgi.nova.core_api.resources.dtos

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier

data class ResourceDifficultyEditionDto(
    val resourceId: ResourceIdentifier,
    val difficultyId: DifficultyIdentifier,
    val startValue: Int
)