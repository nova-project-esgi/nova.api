package nova.api

import com.google.inject.AbstractModule
import com.google.inject.Guice
import io.ktor.application.*
import nova.api.adapters.AdaptersModule
import nova.api.domain.DomainModule

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    Guice.createInjector(MainModule(this), AdaptersModule(), DomainModule())

}

class MainModule(private val application: Application) : AbstractModule() {
    override fun configure() {
        bind(Application::class.java).toInstance(application);
    }
}

