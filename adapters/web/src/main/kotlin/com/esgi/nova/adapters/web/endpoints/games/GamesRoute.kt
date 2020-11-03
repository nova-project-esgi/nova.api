package com.esgi.nova.adapters.web.endpoints.games

import com.esgi.nova.adapters.web.extensions.createdIn
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IGameService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class GamesRoute @Inject constructor(application: Application, gameService: IGameService) {

    init {
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    post("/games") {
                        val game = call.receive<GameCmdDto>()
                        val createdGame = gameService.create(game)
                        createdGame?.let {
                            call.createdIn(GameLocation(id = createdGame.id))
                        }
                    }
                }
                get<GameLocation> {
                    val game = gameService.getOne(it.id)
                    game?.let {
                        call.respond(game)
                    }
                }
            }
        }
    }
}