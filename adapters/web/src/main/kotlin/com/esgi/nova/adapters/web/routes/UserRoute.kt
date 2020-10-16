package com.esgi.nova.adapters.web.routes

import com.esgi.nova.adapters.web.authentication.JWTAuthentication
import com.esgi.nova.adapters.web.mappers.UserMapper
import com.esgi.nova.ports.provided.services.IUserService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.routing.*

class UserRoute @Inject constructor(
    application: Application,
    userService: IUserService,
    userMapper: UserMapper,
    jwtAuthentication: JWTAuthentication
) {
    init {
        application.routing {
            route("/users") {

            }
        }
    }
}