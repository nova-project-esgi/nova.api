package com.esgi.nova.adapters.web

import com.esgi.nova.adapters.web.endpoints.auth.AuthRoute
import com.esgi.nova.adapters.web.endpoints.choice_resources.ChoiceResourcesRoute
import com.esgi.nova.adapters.web.endpoints.choice_translations.ChoiceTranslationsRoute
import com.esgi.nova.adapters.web.endpoints.choices.ChoicesRoute
import com.esgi.nova.adapters.web.endpoints.event_translations.EventTranslationsRoute
import com.esgi.nova.adapters.web.endpoints.events.EventsRoute
import com.esgi.nova.adapters.web.endpoints.game_events.GameEventsRoute
import com.esgi.nova.adapters.web.endpoints.games.GamesRoute
import com.esgi.nova.adapters.web.endpoints.languages.LanguageRoute
import com.esgi.nova.adapters.web.endpoints.resource_translations.ResourceTranslationsRoute
import com.esgi.nova.adapters.web.endpoints.resources.ResourcesRoute
import com.esgi.nova.adapters.web.endpoints.users.UsersRoute
import com.esgi.nova.adapters.web.mappers.UserMapper
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
        bind(AuthRoute::class.java).asEagerSingleton()
        bind(ChoicesRoute::class.java).asEagerSingleton()
        bind(ChoiceTranslationsRoute::class.java).asEagerSingleton()
        bind(ChoiceResourcesRoute::class.java).asEagerSingleton()
        bind(EventsRoute::class.java).asEagerSingleton()
        bind(EventTranslationsRoute::class.java).asEagerSingleton()
        bind(GamesRoute::class.java).asEagerSingleton()
        bind(GameEventsRoute::class.java).asEagerSingleton()
        bind(LanguageRoute::class.java).asEagerSingleton()
        bind(ResourcesRoute::class.java).asEagerSingleton()
        bind(ResourceTranslationsRoute::class.java).asEagerSingleton()
        bind(UsersRoute::class.java).asEagerSingleton()

        bind(UserMapper::class.java).to(Mappers.getMapperClass(UserMapper::class.java))
    }

}
