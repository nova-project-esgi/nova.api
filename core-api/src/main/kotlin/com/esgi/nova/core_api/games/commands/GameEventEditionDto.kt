package com.esgi.nova.core_api.games.commands

import com.esgi.nova.core_api.events.commands.EventIdentifier
import java.time.LocalDateTime

data class GameEventEditionDto(
    val eventId: EventIdentifier,
    val linkTime: LocalDateTime
)