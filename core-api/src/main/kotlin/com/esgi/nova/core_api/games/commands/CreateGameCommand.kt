package com.esgi.nova.core_api.games.commands

import com.esgi.nova.core_api.difficulty.DifficultyIdentifier
import com.esgi.nova.core_api.games.GameIdentifier
import com.esgi.nova.core_api.user.UserIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateGameCommand(
    @TargetAggregateIdentifier val gameId: GameIdentifier,
    val userId: UserIdentifier,
    val difficultyId: DifficultyIdentifier
) {
}