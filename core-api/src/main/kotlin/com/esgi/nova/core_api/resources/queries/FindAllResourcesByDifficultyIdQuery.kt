package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier

data class FindAllResourcesByDifficultyIdQuery(val difficultyId: DifficultyIdentifier)
