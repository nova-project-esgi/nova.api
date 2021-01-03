package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.games.commands.GameIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import java.io.Serializable

data class UpdatedGameResourceEvent(val gameId: GameIdentifier, val resourceId: ResourceIdentifier, val total: Int): Serializable
