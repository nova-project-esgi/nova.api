package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.UserDto

interface IUserPersistence {
    fun getAll(): List<UserDto>
    fun getOrPut(userDto: UserDto): UserDto
}