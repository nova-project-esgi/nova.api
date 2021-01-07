package com.esgi.nova.core_api.games.dtos

import com.esgi.nova.core_api.resources.ResourceIdentifier

data class GameResourceEditionDto(
    val resourceId: ResourceIdentifier,
    val total: Int
)

