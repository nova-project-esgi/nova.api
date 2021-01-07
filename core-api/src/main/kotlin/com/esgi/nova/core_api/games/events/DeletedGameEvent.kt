package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.games.GameIdentifier
import java.io.Serializable

data class DeletedGameEvent(val gameId: GameIdentifier) : Serializable
