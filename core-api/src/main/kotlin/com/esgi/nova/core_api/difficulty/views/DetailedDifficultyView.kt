package com.esgi.nova.core_api.difficulty.views

import java.util.*

data class DetailedDifficultyView(
    val id: UUID,
    val translations: List<DifficultyTranslationViewWithLanguage>,
    val rank: Int,
    val isDefault: Boolean
) {
}