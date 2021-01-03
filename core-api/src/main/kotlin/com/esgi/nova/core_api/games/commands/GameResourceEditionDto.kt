package com.esgi.nova.core_api.games.commands

import com.esgi.nova.core_api.resources.commands.ResourceIdentifier

data class GameResourceEditionDto(
    val resourceId: ResourceIdentifier,
    val total: Int
)

