package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.games.GameIdentifier
import java.io.Serializable

data class RemovedGameEventEvent(val gameId: GameIdentifier, val eventId: EventIdentifier) : Serializable


