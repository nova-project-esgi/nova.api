package com.esgi.nova.core_api.difficulty.events

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import java.io.Serializable

data class DeletedDifficultyEvent(
    val difficultyId: DifficultyIdentifier
) : Serializable