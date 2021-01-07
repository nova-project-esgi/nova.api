package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.games.GameIdentifier

data class FindAllEventsByGameIdOrderByLinkTimeDescQuery(val gameId: GameIdentifier)