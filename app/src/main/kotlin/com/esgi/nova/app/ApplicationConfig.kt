package com.esgi.nova.app

import arrow.core.Either
import com.esgi.nova.adapters.web.authentication.JWTAuthentication
import com.esgi.nova.adapters.web.authorization.RoleAuthorization
import com.esgi.nova.ports.provided.services.IUserService
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.util.*
import java.text.DateFormat
import com.fasterxml.jackson.module.kotlin.*
import java.util.*
import javax.naming.AuthenticationException

@KtorExperimentalAPI
class ApplicationConfig @Inject constructor(
    application: Application,
    jwtAuthentication: JWTAuthentication,
    userService: IUserService
) {
    init {

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
                jwt {
                    verifier(jwtAuthentication.verifier)
                    validate {
                        UserIdPrincipal(it.payload.getClaim("name").asString())
                    }
                }
            }
            install(RoleAuthorization) {
                validate { allowedRoles ->
                    val principal = principal<UserIdPrincipal>()
                    val result = principal?.let {
                        userService.hasSameRole(principal.name, allowedRoles)
                    }
                    if (result == true) {
                        Either.right(Unit)
                    } else {
                        Either.left("User has not sufficient rights")
                    }
                }
            }

            install(DataConversion) {
                convert<UUID> {
                    decode { values, _ ->
                        values.singleOrNull()?.let {
                            UUID.fromString(it)
                        }
                    }
                    encode { value ->
                        when (value) {
                            null -> listOf()
                            is UUID -> listOf(value.toString())
                            else -> throw DataConversionException("Cannot convert $value as UUID")
                        }
                    }
                }
            }
            install(Locations)

            install(ContentNegotiation) {
                jackson {
                    enable(SerializationFeature.INDENT_OUTPUT)
                    registerModule(KotlinModule())
                    registerModule(JavaTimeModule())
                    dateFormat = DateFormat.getDateInstance()
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


