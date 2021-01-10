package com.esgi.nova.application.services.difficulties.models

data class DetailedDifficultyForEdition(
    val translations: List<DifficultyTranslationForEdition>,
    val rank: Int,
    val isDefault: Boolean
)
