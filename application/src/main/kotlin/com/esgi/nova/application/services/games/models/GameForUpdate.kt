package com.esgi.nova.application.services.games.models

data class GameForUpdate(
    val resources: List<GameResourceEdition>,
    val events: List<GameEventEdition>,
    val duration: Int,
    val isEnded: Boolean?
)