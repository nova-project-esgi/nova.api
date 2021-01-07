package com.esgi.nova.core_api.resources.events

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import java.io.Serializable

data class DeletedResourceDifficultyEvent(
    val resourceId: ResourceIdentifier,
    val difficultyId: DifficultyIdentifier
) : Serializable