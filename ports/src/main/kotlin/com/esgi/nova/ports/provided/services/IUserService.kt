package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.user.UserRegisterCmdDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import com.esgi.nova.ports.provided.dtos.user.UserLoginCmdDto
import com.esgi.nova.ports.provided.enums.Role
import java.util.*

interface IUserService : ICrudService<UUID, UserRegisterCmdDto, UserDto> {
    fun signIn(userRegisterDto: UserLoginCmdDto): UserDto?
    fun getByUsername(userName: String): UserDto?
    fun hasSameRole(username: String, role: Iterable<Role>): Boolean
}