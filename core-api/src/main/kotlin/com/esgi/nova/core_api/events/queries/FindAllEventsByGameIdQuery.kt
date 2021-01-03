package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.games.commands.GameIdentifier

data class FindAllEventsByGameIdQuery(val gameId: GameIdentifier)
