package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.games.GameIdentifier
import java.io.Serializable

data class UpdatedGameStatusEvent(val gameId: GameIdentifier, val isEnded: Boolean): Serializable