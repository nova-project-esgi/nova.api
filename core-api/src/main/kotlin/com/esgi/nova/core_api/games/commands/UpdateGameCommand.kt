package com.esgi.nova.core_api.games.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateGameCommand(
    @TargetAggregateIdentifier val gameId: GameIdentifier,
    val duration: Int,
    val isEnded: Boolean,
    val resources: List<GameResourceEditionDto>,
    val events: List<GameEventEditionDto>
)