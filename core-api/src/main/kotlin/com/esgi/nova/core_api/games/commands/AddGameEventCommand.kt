package com.esgi.nova.core_api.games.commands

import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.games.GameIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class AddGameEventCommand(@TargetAggregateIdentifier val gameId: GameIdentifier, val eventId: EventIdentifier)
