package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.UserDto

interface IUserService {
    fun getAll(): List<UserDto>
    fun getOrPut(userDto: UserDto): UserDto
}