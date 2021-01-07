package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.games.GameIdentifier
import java.io.Serializable
import java.time.LocalDateTime

data class AddedGameEventEvent(val gameId: GameIdentifier, val eventId: EventIdentifier, val linkTime: LocalDateTime) :
    Serializable
