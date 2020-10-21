package com.esgi.nova.adapters.web

import com.esgi.nova.adapters.web.mappers.UserMapper
import com.esgi.nova.adapters.web.endpoints.auth.AuthRoute
import com.esgi.nova.adapters.web.endpoints.events.EventsRoute
import com.esgi.nova.adapters.web.endpoints.game_events.GameEventsRoute
import com.esgi.nova.adapters.web.endpoints.games.GamesRoute
import com.esgi.nova.adapters.web.endpoints.users.UsersRoute
import com.google.inject.AbstractModule
import io.ktor.locations.*
import io.ktor.util.*
import org.mapstruct.factory.Mappers
import kotlin.time.ExperimentalTime

class WebModule() : AbstractModule() {
    @ExperimentalTime
    @KtorExperimentalLocationsAPI
    @KtorExperimentalAPI
    override fun configure() {
        bind(UsersRoute::class.java).asEagerSingleton()
        bind(AuthRoute::class.java).asEagerSingleton()
        bind(EventsRoute::class.java).asEagerSingleton()
        bind(GamesRoute::class.java).asEagerSingleton()
        bind(GameEventsRoute::class.java).asEagerSingleton()
        bind(UserMapper::class.java).to(Mappers.getMapperClass(UserMapper::class.java))
    }

}
