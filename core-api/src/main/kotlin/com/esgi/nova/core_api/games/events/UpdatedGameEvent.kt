package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.games.commands.GameIdentifier
import java.io.Serializable

data class UpdatedGameEvent(val gameId: GameIdentifier, val duration: Int, val isEnded: Boolean): Serializable
