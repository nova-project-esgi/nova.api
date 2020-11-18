package com.esgi.nova.adapters.web.endpoints.auth

import com.esgi.nova.adapters.web.authentication.Encryption
import com.esgi.nova.adapters.web.authentication.JWTAuthentication
import com.esgi.nova.adapters.web.domain.JWT
import com.esgi.nova.adapters.web.endpoints.users.UserLocationByUUID
import com.esgi.nova.adapters.web.mappers.UserMapper
import com.esgi.nova.ports.provided.dtos.user.UserLoginCmdDto
import com.esgi.nova.ports.provided.dtos.user.UserRegisterCmdDto
import com.esgi.nova.ports.provided.services.IUserService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class AuthRoute @Inject constructor(
    application: Application,
    userService: IUserService,
    userMapper: UserMapper,
    jwtAuthentication: JWTAuthentication
) {
    init {
        application.routing {
            route("/auth") {
                post("/login") {
                    val post = call.receive<UserLoginCmdDto>()
                    post.password = Encryption.md5(post.password)
                    val user = userService.signIn(post)
                    if (user?.password != post.password) {
                        call.respond(HttpStatusCode.Unauthorized)
                    } else {
                        call.respond(userMapper.toResume(user, JWT(jwtAuthentication.sign(user.username))))
                    }
                }
                post("/register") {
                    val post = call.receive<UserRegisterCmdDto>()
                    post.password = Encryption.md5(post.password)
                    val user = userService.create(post)
                    user?.let {
                        val url = locations.href(UserLocationByUUID(user.id))
                        call.response.headers.append(HttpHeaders.Location, url)
                        call.
respond(userMapper.toResume(user, JWT(jwtAuthentication.sign(user.username))))
                    }
                }
            }
        }
    }
}