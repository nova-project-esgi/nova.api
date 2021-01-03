package com.esgi.nova.core_api.difficulty.events

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import java.io.Serializable

data class UpdatedDifficultyEvent(
    val difficultyId: DifficultyIdentifier,
    val isDefault: Boolean,
    val rank: Int
) : Serializable