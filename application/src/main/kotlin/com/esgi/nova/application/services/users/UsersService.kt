package com.esgi.nova.application.services.users

import com.esgi.nova.application.extensions.queryPage
import com.esgi.nova.application.services.users.exceptions.UserNotFoundByIdException
import com.esgi.nova.application.services.users.models.ConnectedUser
import com.esgi.nova.application.services.users.models.UserEdition
import com.esgi.nova.application.services.users.models.UserLogin
import com.esgi.nova.application.services.users.models.UserRole
import com.esgi.nova.common.security.Encryption
import com.esgi.nova.common.security.JWTAuthentication
import com.esgi.nova.core.domain.users.exceptions.UserAlreadyExistsException
import com.esgi.nova.core.domain.users.exceptions.UserNotFoundByUsernameAndPasswordException
import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.UserIdentifier
import com.esgi.nova.core_api.user.commands.CreateUserCommand
import com.esgi.nova.core_api.user.commands.DeleteUserCommand
import com.esgi.nova.core_api.user.commands.UpdateUserCommand
import com.esgi.nova.core_api.user.commands.UpdateUserRoleCommand
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByTokenException
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByUsernameException
import com.esgi.nova.core_api.user.queries.*
import com.esgi.nova.core_api.user.views.UserAdminEditView
import com.esgi.nova.core_api.user.views.UserCredential
import com.esgi.nova.core_api.user.views.UserResume
import com.esgi.nova.core_api.user.views.UserUsername
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
open class UsersService(private val commandGateway: CommandGateway, private val queryGateway: QueryGateway) {
    open fun delete(id: UUID) {
        commandGateway.sendAndWait<DeleteUserCommand>(DeleteUserCommand(userId = UserIdentifier(id.toString())))
    }

    open fun getCredentialByUsername(username: String): UserCredential {
        queryGateway.query<UserCredential, FindUserByUsernameQuery>(FindUserByUsernameQuery(username = username))
            .join()?.let { return it }
        throw UserNotFoundByUsernameException()
    }

    open fun getResumeByUsername(username: String): UserResume {
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
                    token = JWTAuthentication.sign(user.username),
                    id = user.id
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
                    token = JWTAuthentication.sign(user.username),
                    id = user.id
                )
            }
        throw UserNotFoundByUsernameAndPasswordException(credentials.username, credentials.password)
    }

    open fun register(user: UserEdition): ConnectedUser {
        var role = Role.USER
        if (!queryGateway.query(HasUsersQuery(), Boolean::class.java).join()) {
            role = Role.ADMIN
        }
        if (queryGateway.query<UserResume, FindUserByUsernameQuery>(FindUserByUsernameQuery(user.username))
                .join() != null
            || queryGateway.query<UserResume, FindUserByEmailQuery>(FindUserByEmailQuery(user.email))
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
        ).let { userId ->
            return ConnectedUser(
                email = user.email,
                role = role,
                username = user.username,
                token = JWTAuthentication.sign(user.username),
                id = userId.toUUID()
            )
        }

    }

    open fun update(id: UUID, user: UserEdition) {
        val userByEmail =
            queryGateway.query<UserResume?, FindUserByUsernameQuery>(FindUserByUsernameQuery(user.username))
                .join()
        val userByPassword = queryGateway.query<UserResume?, FindUserByEmailQuery>(FindUserByEmailQuery(user.email))
            .join()
        if ((userByEmail != null && userByEmail.id != id) || (userByPassword != null && userByPassword.id != id)) {
            throw UserAlreadyExistsException(user.username)
        }
        commandGateway.sendAndWait<UserIdentifier>(
            UpdateUserCommand(
                userId = UserIdentifier(id.toString()),
                password = user.password,
                email = user.email,
                username = user.username
            )
        )
    }

    fun updateUserRole(id: UUID, userRole: UserRole) {
        if (!queryGateway.query(UserExistsByIdQuery(UserIdentifier(id.toString())), Boolean::class.java).join()) {
            throw UserNotFoundByIdException()
        }
        commandGateway.sendAndWait<UserIdentifier>(UpdateUserRoleCommand(UserIdentifier(id.toString()), userRole.role))
    }

    fun getPaginatedUserUserAdminEditsByUsername(page: Int, size: Int, username: String): PageBase<UserAdminEditView> {
        return queryGateway.queryPage<UserAdminEditView, FindPaginatedUserAdminEditsByUsernameQuery>(
            FindPaginatedUserAdminEditsByUsernameQuery(
                username = username, page = page, size = size
            )
        ).join()
    }

    fun getPaginatedUserUsernamesByUsername(page: Int, size: Int, username: String): PageBase<UserUsername> {
        return queryGateway.queryPage<UserUsername, FindPaginatedUserUsernamesByUsernameQuery>(
            FindPaginatedUserUsernamesByUsernameQuery(username = username, page = page, size = size)
        ).join()
    }
}

