
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.LoginRegister
import nova.api.ports.provided.IUserService

class UserRoute @Inject constructor(application: Application, userService: IUserService) {
    init {
        application.routing {
            get("/") {
                val result = userService.getAllUsers()
                call.respond(result)
            }
            get("/json/jackson") {
                call.respond(mapOf("hello" to "world"))
            }
            post("/login-register"){
                val post = call.receive<LoginRegister>()
            }
        }
    }
}