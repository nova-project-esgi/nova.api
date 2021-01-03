package com.esgi.nova.core_api.events.queries

import com.esgi.nova.core_api.games.commands.GameIdentifier

data class FindAllTranslatedEventsByGameIdAndLanguageQuery(val gameId: GameIdentifier, val language: String)
