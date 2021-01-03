package com.esgi.nova.core_api.resources.views

import com.esgi.nova.core_api.difficulty.views.DetailedDifficultyView
import java.util.*

data class ResourceDifficultyView(
    val resourceId: UUID,
    val startValue: Int,
    val difficulty: DetailedDifficultyView
)
