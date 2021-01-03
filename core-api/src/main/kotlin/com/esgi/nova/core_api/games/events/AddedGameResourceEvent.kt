package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.games.commands.GameIdentifier
import com.esgi.nova.core_api.resources.commands.ResourceIdentifier
import org.springframework.core.io.Resource
import java.io.Serializable

data class AddedGameResourceEvent(val gameId: GameIdentifier, val resourceId: ResourceIdentifier, val total: Int): Serializable
