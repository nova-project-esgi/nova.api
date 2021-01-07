package com.esgi.nova.application.uses_cases.games.models

import java.time.LocalDateTime
import java.util.*

data class GameEventEdition(
    val eventId: UUID,
    val linkTime: LocalDateTime?
)