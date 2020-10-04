package nova.api.adapters

import com.google.inject.AbstractModule
import io.ktor.util.*
import nova.api.adapters.routes.HelloRoutes
import org.jetbrains.exposed.sql.Database

class AdaptersModule: AbstractModule (){
    @KtorExperimentalAPI
    override fun configure() {
        bind(ApplicationConfig::class.java).asEagerSingleton()
        bind(HelloRoutes::class.java).asEagerSingleton()

    }

}