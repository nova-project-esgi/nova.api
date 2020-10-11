package com.esgi.nova.adapters.web

import com.esgi.nova.adapters.web.mappers.UserMapper
import com.esgi.nova.adapters.web.routes.UserRoute
import com.google.inject.AbstractModule
import io.ktor.util.*
import org.mapstruct.factory.Mappers

class WebModule() : AbstractModule() {
    @KtorExperimentalAPI
    override fun configure() {
        bind(UserRoute::class.java).asEagerSingleton()
        bind(UserMapper::class.java).to(Mappers.getMapperClass(UserMapper::class.java))
    }

}