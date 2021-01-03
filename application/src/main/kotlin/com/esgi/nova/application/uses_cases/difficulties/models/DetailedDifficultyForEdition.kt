package com.esgi.nova.application.uses_cases.difficulties.models

import com.esgi.nova.application.uses_cases.resources.models.ResourceTranslationForEdition
import java.util.*

data class DetailedDifficultyForEdition(
    val translations: List<DifficultyTranslationForEdition>,
    val rank: Int,
    val isDefault: Boolean
)
