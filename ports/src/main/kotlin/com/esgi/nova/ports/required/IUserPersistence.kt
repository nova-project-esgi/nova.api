package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.UserDto

interface IUserPersistence {
    fun getAllUsers(): List<UserDto>
    fun getOrPutUser(userDto: UserDto): UserDto
}