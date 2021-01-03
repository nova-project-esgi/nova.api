package com.esgi.nova.core_api.difficulty.views

import java.util.*

data class DetailedDifficultyWithAvailableActionsView(
    val id: UUID,
    val translations: List<DifficultyTranslationViewWithLanguage>,
    val canDelete: Boolean,
    val canSetDefault: Boolean,
    val rank: Int,
    val isDefault: Boolean
) {
}
