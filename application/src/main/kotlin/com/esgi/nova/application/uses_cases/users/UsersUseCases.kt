package com.esgi.nova.application.uses_cases.users

import com.esgi.nova.common.security.Encryption
import com.esgi.nova.common.security.JWTAuthentication
import com.esgi.nova.core.domain.users.exceptions.UserAlreadyExistsException
import com.esgi.nova.core.domain.users.exceptions.UserNotFoundByUsernameAndPasswordException
import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.UserIdentifier
import com.esgi.nova.core_api.user.commands.CreateUserCommand
import com.esgi.nova.core_api.user.commands.DeleteUserCommand
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByTokenException
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByUsernameException
import com.esgi.nova.core_api.user.queries.FindUserByUsernameAndPasswordQuery
import com.esgi.nova.core_api.user.queries.FindUserByUsernameQuery
import com.esgi.nova.core_api.user.queries.HasUsersQuery
import com.esgi.nova.core_api.user.views.UserCredential
import com.esgi.nova.core_api.user.views.UserResume
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
open class UsersUseCases(private val commandGateway: CommandGateway, private val queryGateway: QueryGateway) {
    open fun delete(id: UUID) {
        commandGateway.sendAndWait<DeleteUserCommand>(DeleteUserCommand(userId = UserIdentifier(id.toString())))
    }

    open fun getCredentialByUsername(username: String): UserCredential{
        queryGateway
            queryGateway.query<UserCredential, FindUserByUsernameQuery>(FindUserByUsernameQuery(username = username))
            .join()?.let { return it }
        throw UserNotFoundByUsernameException()
    }
    open fun getResumeByUsername(username: String): UserResume{
        queryGateway
        queryGateway.query<UserResume, FindUserByUsernameQuery>(FindUserByUsernameQuery(username = username))
            .join()?.let { return it }
        throw UserNotFoundByUsernameException()
    }

    open fun getByToken(token: String): ConnectedUser {
        val username = JWTAuthentication.parse(token)
        queryGateway.query<UserResume, FindUserByUsernameQuery>(FindUserByUsernameQuery(username = username)).join()
            ?.let { user ->
                return ConnectedUser(
                    email = user.email,
                    role = user.role,
                    username = user.username,
                    token = JWTAuthentication.sign(user.username)
                )

            }
        throw UserNotFoundByTokenException()
    }

    fun login(credentials: UserLogin): ConnectedUser {
        val user = queryGateway
            .query<UserResume?, FindUserByUsernameAndPasswordQuery>(
                FindUserByUsernameAndPasswordQuery(
                    username = credentials.username,
                    password = Encryption.md5(credentials.password)
                )
            )
            .join()
            ?.let { user ->
                return ConnectedUser(
                    email = user.email,
                    role = user.role,
                    username = user.username,
                    token = JWTAuthentication.sign(user.username)

                )
            }
        throw UserNotFoundByUsernameAndPasswordException(credentials.username, credentials.password)
    }

    open fun register(user: UserRegister): ConnectedUser {
        var role = Role.USER
        if (!queryGateway.query(HasUsersQuery(), Boolean::class.java).join()) {
            role = Role.ADMIN
        }
        if (queryGateway.query<UserResume, FindUserByUsernameQuery>(FindUserByUsernameQuery(user.username))
                .join() != null
        ) {
            throw UserAlreadyExistsException(user.username)
        }
        commandGateway.sendAndWait<UserIdentifier>(
            CreateUserCommand(
                userId = UserIdentifier(),
                username = user.username,
                email = user.email,
                role = role,
                password = Encryption.md5(user.password)
            )
        ).let {
            return ConnectedUser(
                email = user.email,
                role = role,
                username = user.username,
                token = JWTAuthentication.sign(user.username)
            )
        }

    }
}

