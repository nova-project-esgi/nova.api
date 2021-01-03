package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.difficulty.commands.DifficultyIdentifier
import com.esgi.nova.core_api.games.commands.GameIdentifier
import com.esgi.nova.core_api.user.UserIdentifier
import java.io.Serializable

data class CreatedGameEvent(val gameId: GameIdentifier, val userId: UserIdentifier, val difficultyId: DifficultyIdentifier): Serializable
