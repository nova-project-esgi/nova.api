package com.esgi.nova.core_api.difficulty.queries

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier

data class FindOneDifficultyWithAvailableActionsViewQuery(
    val id: DifficultyIdentifier
)

