package nova.api.adapters.routes

import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

class HelloRoutes @Inject constructor(application: Application) {
    init {
        application.routing {
            get("/") {
                call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
            }
            get("/json/jackson") {
                call.respond(mapOf("hello" to "world"))
            }
        }
    }
}