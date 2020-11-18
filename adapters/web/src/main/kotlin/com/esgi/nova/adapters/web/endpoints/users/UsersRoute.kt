package com.esgi.nova.adapters.web.endpoints.users

import com.esgi.nova.adapters.web.authentication.Encryption
import com.esgi.nova.adapters.web.authentication.JWTAuthentication
import com.esgi.nova.adapters.web.domain.JWT
import com.esgi.nova.adapters.web.extensions.rolesAllowed
import com.esgi.nova.adapters.web.mappers.UserMapper
import com.esgi.nova.ports.provided.dtos.user.UserLoginCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IUserService
import com.google.inject.Inject
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
class UsersRoute @Inject constructor(
    application: Application,
    userService: IUserService,
    userMapper: UserMapper,
    jwtAuthentication: JWTAuthentication
) {
    init {
        application.routing {
            get<UserLocationByToken>{ location ->
                val username = jwtAuthentication.decode(location.token);
                username?.let {
                    val user = userService.getByUsername(username);
                    if(user == null)  {
                        call.respond(HttpStatusCode.Unauthorized)
                    } else {
                        call.respond(userMapper.toResume(user, JWT(jwtAuthentication.sign(user.username))))
                    }
                }

            }
            authenticate {
                rolesAllowed(Role.ADMIN) {
                    get<UsersLocation> {
                        val users = userService.getAll()
                        call.respond(users)
                    }
                }
            }
        }
    }
}