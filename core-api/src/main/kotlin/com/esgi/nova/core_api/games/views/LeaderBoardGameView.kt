package com.esgi.nova.core_api.games.views

import com.esgi.nova.core_api.user.views.UserResume
import java.util.*

data class LeaderBoardGameView(
    val id: UUID,
    val user: UserResume,
    val duration: Int,
    val difficultyId: UUID,
    val resources: List<GameResourceView>,
    val eventCount: Int
)