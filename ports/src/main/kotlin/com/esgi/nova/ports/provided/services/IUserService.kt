package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.user.UserCmdDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import com.esgi.nova.ports.provided.enums.Role
import java.util.*

interface IUserService : IGetAll<UserDto>, IUpdateOne<UserCmdDto, UUID, UserDto> {
    fun getOrCreate(userDto: UserCmdDto): UserDto?
    fun hasSameRole(username: String, role: Iterable<Role>): Boolean
}