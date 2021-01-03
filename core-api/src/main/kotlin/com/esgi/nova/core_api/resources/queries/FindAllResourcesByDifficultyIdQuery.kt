package com.esgi.nova.core_api.resources.queries

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier

data class FindAllResourcesByDifficultyIdQuery(val difficultyId: DifficultyIdentifier)
