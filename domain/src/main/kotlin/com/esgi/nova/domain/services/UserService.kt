package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.user.UserRegisterCmdDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import com.esgi.nova.ports.provided.dtos.user.UserLoginCmdDto
import com.esgi.nova.ports.provided.enums.Role
import com.esgi.nova.ports.provided.services.IUserService
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.Inject
import java.util.*


class UserService @Inject constructor(override val persistence: IUserPersistence) :
    BaseService<UUID, UserRegisterCmdDto, UserDto>(persistence), IUserService {
    override fun signIn(userRegisterDto: UserLoginCmdDto): UserDto? = persistence.getByUsernameAndPassword(userRegisterDto.username, userRegisterDto.password)
    override fun hasSameRole(username: String, role: Iterable<Role>): Boolean {
        val user = persistence.getByName(username)
        user?.let {
            return role.any { r -> r == it.role }
        }
        return false
    }
}