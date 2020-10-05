import com.google.inject.AbstractModule
import io.ktor.config.*
import io.ktor.util.*

class WebModule: AbstractModule() {
    @KtorExperimentalAPI
    override fun configure() {
        bind(UserRoute::class.java).asEagerSingleton()
    }

}