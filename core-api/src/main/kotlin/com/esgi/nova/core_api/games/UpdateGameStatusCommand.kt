package com.esgi.nova.core_api.games

import com.esgi.nova.core_api.games.GameIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateGameStatusCommand(@TargetAggregateIdentifier val gameId: GameIdentifier, val isEnded: Boolean)