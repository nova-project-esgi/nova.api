package com.esgi.nova.adapters.web.endpoints.games

import com.esgi.nova.adapters.web.authorization.RoleAuthorization
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IGameService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@ExperimentalTime
@KtorExperimentalLocationsAPI
class GamesRoute @Inject constructor(application: Application, gameService: IGameService) {

    init {
        val log = LoggerFactory.getLogger(GamesRoute::class.java)
        application.routing {
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    post("/games") {
                        val game = call.receive<GameCmdDto>()
                        val createdGame = gameService.create(game)
                        createdGame?.let {
                            val url = application.locations.href(GameLocation(id = createdGame.id))
                            call.response.headers.append(HttpHeaders.Location, url)
                            call.respondText("Created")
                        }
                    }
                }
                get<GameLocation> {
                    val game = gameService.getOne(it.id)
                    game?.let{
                        call.respond(game)
                    }

                }
            }
        }
    }
}