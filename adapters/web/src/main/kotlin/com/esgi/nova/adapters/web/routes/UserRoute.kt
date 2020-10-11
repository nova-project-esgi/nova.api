package com.esgi.nova.adapters.web.routes

import com.esgi.nova.adapters.web.authentication.JWTAuthentication
import com.esgi.nova.adapters.web.mappers.UserMapper
import com.esgi.nova.adapters.web.models.LoginRegister
import com.esgi.nova.ports.provided.IUserService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

class UserRoute @Inject constructor(
    application: Application,
    userService: IUserService,
    userMapper: UserMapper,
    jwtAuthentication: JWTAuthentication
) {
    init {
        application.routing {
            get("/") {
                val result = userService.getAllUsers()
                call.respond(result)
            }
            get("/json/jackson") {
                call.respond(mapOf("hello" to "world"))
            }
            post("/login-register") {
                val post = call.receive<LoginRegister>()
                val user = userService.getOrPutUser(userMapper.toDto(post))
                if (user.password != post.password) error("Invalid credentials")
                call.respond(mapOf("token" to jwtAuthentication.sign(user.username)))
            }
        }
    }
}