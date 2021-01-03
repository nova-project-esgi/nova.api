package com.esgi.nova.core_api.resources.commands

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier

data class ResourceDifficultyEditionDto(val resourceId: ResourceIdentifier, val difficultyId: DifficultyIdentifier, val startValue: Int)