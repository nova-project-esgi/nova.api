package com.esgi.nova.application.uses_cases.resources.models

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import java.util.*

data class DetailedResourceForEdition(
    val translations: List<ResourceTranslationForEdition>,
    val difficulties: List<ResourceDifficultyEdition>
    ) {
}