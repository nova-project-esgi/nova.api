package com.esgi.nova.application.uses_cases.difficulties.models

data class DetailedDifficultyForEdition(
    val translations: List<DifficultyTranslationForEdition>,
    val rank: Int,
    val isDefault: Boolean
)
