package com.esgi.nova.core_api.resources.events

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.io.Serializable

data class DeletedResourceDifficultyEvent(
    val resourceId: ResourceIdentifier,
    val difficultyId: DifficultyIdentifier
) : Serializable