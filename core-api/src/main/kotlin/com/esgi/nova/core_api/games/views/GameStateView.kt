package com.esgi.nova.core_api.games.views

import java.util.*

data class GameStateView(
    val id: UUID,
    val userId:UUID,
    val duration: Int,
    val isEnded: Boolean,
    val difficultyId: UUID,
    val events: List<GameEventView>,
    val resources: List<GameResourceView>
)