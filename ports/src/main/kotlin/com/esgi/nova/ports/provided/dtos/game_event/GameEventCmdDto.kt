package com.esgi.nova.ports.provided.dtos.game_event

import java.time.LocalDateTime
import java.util.*

data class GameEventCmdDto(var gameId: UUID, var eventId: UUID, var linkTime: LocalDateTime?) {
}