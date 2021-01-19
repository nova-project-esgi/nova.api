package com.esgi.nova.core_api.games.views

import java.time.LocalDateTime
import java.util.*

data class GameEventView(val eventId: UUID, val linkTime: LocalDateTime)