package com.esgi.nova.adapters.web.endpoints.game_events

import com.esgi.nova.adapters.web.domain.GameEventCmd
import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import com.esgi.nova.ports.provided.services.IGameEventService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class GameEventsRoute @Inject constructor(application: Application, gameEventService: IGameEventService) {

    init {
        application.routing {
            authenticate {
                post<GameEventsLocation> { location ->
                    val gameEvent = call.receive<GameEventCmd>()
                    val gameEventDto =
                        GameEventCmdDto(gameId = location.gID, eventId = location.eID, linkTime = gameEvent.linkTime)
                    val createdGameEvent = gameEventService.create(gameEventDto)
                    createdGameEvent?.let {
                        call.createdIn(location)
                    }
                }
                get<GameEventsLocation> { location ->
                    val gameEvents =
                        gameEventService.getAllFiltered(GameEventsId(eventId = location.eID, gameId = location.gID))
                    call.respond(gameEvents)
                }

                get<GameEventLocation> { location ->
                    val gameEvent = gameEventService.getOne(location.id)
                    gameEvent?.let {
                        call.respond(gameEvent)
                    }
                }
            }
        }
    }
}


