package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.user.UserRegisterCmdDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import java.util.*

interface IUserPersistence :
    ICrudPersistence<UUID, UserRegisterCmdDto, UserDto> {
    fun getByName(username: String): UserDto?
    fun getByUsernameAndPassword(username: String, password:String): UserDto?
}