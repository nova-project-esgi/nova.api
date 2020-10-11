package com.esgi.nova.app

import com.esgi.nova.adapters.exposed.PersistenceModule
import com.esgi.nova.adapters.web.WebModule
import com.esgi.nova.adapters.web.authentication.JWTAuthentication
import com.esgi.nova.domain.DomainModule
import com.google.inject.AbstractModule
import com.google.inject.Guice
import io.ktor.application.*
import io.ktor.util.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
fun Application.module(testing: Boolean = false) {
    Guice.createInjector(
        MainModule(this),
        WebModule(),
        PersistenceModule(
            databaseUrl,
            databaseDriver,
            databaseUsr,
            databasePwd
        ),
        DomainModule()
    )
}

class MainModule(private val application: Application) : AbstractModule() {
    @KtorExperimentalAPI
    override fun configure() {
        val jwt = JWTAuthentication("my-super-secret-for-jwt")
        bind(ApplicationConfig::class.java).asEagerSingleton()
        bind(Application::class.java).toInstance(application)
        bind(JWTAuthentication::class.java).toInstance(jwt)
    }
}

