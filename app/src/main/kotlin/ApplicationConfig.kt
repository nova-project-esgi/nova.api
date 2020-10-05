package nova.api

import authentication.JWTAuthentication
import com.fasterxml.jackson.databind.SerializationFeature
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.util.*
import org.jetbrains.exposed.sql.Database

import javax.naming.AuthenticationException

@KtorExperimentalAPI
class ApplicationConfig @Inject constructor(application: Application) {
    init {
        Database.connect(
            application.databaseUrl, application.databaseDriver,
            user = application.databaseUsr, password = application.databasePwd
        )

        application.apply {
            install(CORS) {
                method(HttpMethod.Options)
                method(HttpMethod.Put)
                method(HttpMethod.Delete)
                method(HttpMethod.Patch)
                header(HttpHeaders.Authorization)
                header("MyCustomHeader")
                allowCredentials = true
                anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
            }

            install(Authentication) {
                jwt{
                    val jwt = JWTAuthentication("my-super-secret-for-jwt")
                    verifier(jwt.verifier)
                    validate{
                        UserIdPrincipal(it.payload.getClaim("name").asString())
                    }
                }
            }

            install(ContentNegotiation) {
                jackson {
                    enable(SerializationFeature.INDENT_OUTPUT)
                }
            }
            install(StatusPages) {
                exception<AuthenticationException> {
                    call.respond(HttpStatusCode.Unauthorized)
                }
                exception<AuthorizationException> {
                    call.respond(HttpStatusCode.Forbidden)
                }
            }
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

@KtorExperimentalAPI
val Application.databaseUrl
    get() = environment.config.property("ktor.database.url").getString()

@KtorExperimentalAPI
val Application.databaseDriver
    get() = environment.config.property("ktor.database.driver").getString()

@KtorExperimentalAPI
val Application.databaseUsr
    get() = environment.config.property("ktor.database.usr").getString()

@KtorExperimentalAPI
val Application.databasePwd
    get() = environment.config.property("ktor.database.pwd").getString()


