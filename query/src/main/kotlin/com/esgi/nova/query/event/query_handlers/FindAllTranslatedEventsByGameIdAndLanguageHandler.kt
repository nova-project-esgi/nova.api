package com.esgi.nova.query.event.query_handlers

import com.esgi.nova.core_api.events.queries.FindAllTranslatedEventsByGameIdAndLanguageQuery
import com.esgi.nova.core_api.events.views.TranslatedEventView
import com.esgi.nova.query.game_event.GameEventRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
open class FindAllTranslatedEventsByGameIdAndLanguageHandler(private val gameEventRepository: GameEventRepository) {

    @QueryHandler
    fun handle(query: FindAllTranslatedEventsByGameIdAndLanguageQuery): List<TranslatedEventView> {
        return gameEventRepository.findAllByGameId(query.gameId.toUUID()).map { it.event.toTranslatedEvent(language = query.language) }
    }
}

