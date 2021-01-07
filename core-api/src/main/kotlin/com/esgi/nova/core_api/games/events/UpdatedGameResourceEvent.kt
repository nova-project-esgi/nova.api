package com.esgi.nova.core_api.games.events

import com.esgi.nova.core_api.games.GameIdentifier
import com.esgi.nova.core_api.resources.ResourceIdentifier
import java.io.Serializable

data class UpdatedGameResourceEvent(val gameId: GameIdentifier, val resourceId: ResourceIdentifier, val total: Int) :
    Serializable
