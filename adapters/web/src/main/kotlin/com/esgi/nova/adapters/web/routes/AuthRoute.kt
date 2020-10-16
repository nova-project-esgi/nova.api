package com.esgi.nova.adapters.web.routes

import com.esgi.nova.adapters.web.authentication.Encryption
import com.esgi.nova.adapters.web.authentication.JWTAuthentication
import com.esgi.nova.adapters.web.mappers.UserMapper
import com.esgi.nova.adapters.web.models.LoginRegister
import com.esgi.nova.ports.provided.services.IUserService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

class AuthRoute @Inject constructor(
    application: Application,
    userService: IUserService,
    userMapper: UserMapper,
    jwtAuthentication: JWTAuthentication
) {
    init {
        application.routing {
            route("/auth") {
                post("/login-register") {
                    val post = call.receive<LoginRegister>()
                    post.password = Encryption.md5(post.password)
                    val user = userService.getOrPut(userMapper.toDto(post))
                    if (user.password != post.password) error("Invalid credentials")
                    call.respond(mapOf("token" to jwtAuthentication.sign(user.username)))
                }
            }
        }
    }
}