package com.esgi.nova.core_api.resources.events

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import java.io.Serializable

data class UpdatedResourceDifficultyEvent(
    val resourceId: ResourceIdentifier,
    val difficultyId: DifficultyIdentifier,
    val startValue: Int
) : Serializable