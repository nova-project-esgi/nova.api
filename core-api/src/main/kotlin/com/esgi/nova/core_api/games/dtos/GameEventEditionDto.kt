package com.esgi.nova.core_api.games.dtos

import com.esgi.nova.core_api.events.EventIdentifier
import java.time.LocalDateTime

data class GameEventEditionDto(
    val eventId: EventIdentifier,
    val linkTime: LocalDateTime
)