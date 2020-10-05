package nova.api

import PersistenceModule
import WebModule
import com.google.inject.AbstractModule
import com.google.inject.Guice
import io.ktor.application.*
import io.ktor.util.*
import nova.api.domain.DomainModule


fun main(args: Array<String>): Unit  {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
fun Application.module(testing: Boolean = false) {

    Guice.createInjector(MainModule(this), WebModule(), PersistenceModule(), DomainModule())
}

class MainModule(private val application: Application) : AbstractModule() {
    @KtorExperimentalAPI
    override fun configure() {
        bind(ApplicationConfig::class.java).asEagerSingleton()
        bind(Application::class.java).toInstance(application)
    }
}

