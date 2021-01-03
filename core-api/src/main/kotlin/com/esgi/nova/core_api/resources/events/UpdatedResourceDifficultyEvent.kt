package com.esgi.nova.core_api.resources.events

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.io.Serializable

data class UpdatedResourceDifficultyEvent(
    val resourceId: ResourceIdentifier,
    val difficultyId: DifficultyIdentifier,
    val startValue: Int
) : Serializable