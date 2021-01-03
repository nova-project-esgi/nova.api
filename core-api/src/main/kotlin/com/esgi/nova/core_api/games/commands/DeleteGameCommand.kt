package com.esgi.nova.core_api.games.commands

import com.esgi.nova.core_api.games.commands.GameIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class DeleteGameCommand(@TargetAggregateIdentifier val gameId: GameIdentifier) {
}