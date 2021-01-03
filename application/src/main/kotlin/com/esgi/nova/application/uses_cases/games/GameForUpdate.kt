package com.esgi.nova.application.uses_cases.games

data class GameForUpdate(
    val resources: List<GameResourceEdition>,
    val events: List<GameEventEdition>,
    val duration: Int,
    val isEnded: Boolean?
)