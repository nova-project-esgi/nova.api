package com.esgi.nova.core_api.games.views

import java.util.*

data class GameView(
    val id: UUID,
    val userId: UUID,
    val duration: Int,
    val difficultyId: UUID,
    val resourceIds: List<UUID>,
    val isEnded: Boolean,
    val eventIds: List<UUID>
)
