package com.esgi.nova.core_api.difficulty.events

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import java.io.Serializable

data class UpdatedDifficultyEvent(
    val difficultyId: DifficultyIdentifier,
    val isDefault: Boolean,
    val rank: Int
) : Serializable