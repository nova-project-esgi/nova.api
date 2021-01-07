package com.esgi.nova.core_api.games.commands

import com.esgi.nova.core_api.games.GameIdentifier
import com.esgi.nova.core_api.games.dtos.GameEventEditionDto
import com.esgi.nova.core_api.games.dtos.GameResourceEditionDto
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateGameCommand(
    @TargetAggregateIdentifier val gameId: GameIdentifier,
    val duration: Int,
    val isEnded: Boolean,
    val resources: List<GameResourceEditionDto>,
    val events: List<GameEventEditionDto>
)