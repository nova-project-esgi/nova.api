package com.esgi.nova.core_api.difficulty.views

import java.util.*

data class TranslatedDifficultyView(
    val id: UUID,
    val language: String,
    val name: String,
    val resources: List<DifficultyResourceView>
) {

}
