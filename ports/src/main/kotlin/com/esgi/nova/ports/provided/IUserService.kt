package com.esgi.nova.ports.provided

interface IUserService {
    fun getAllUsers(): List<UserDto>
    fun getOrPutUser(userDto: UserDto): UserDto
}