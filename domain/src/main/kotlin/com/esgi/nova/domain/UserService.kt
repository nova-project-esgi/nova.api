package com.esgi.nova.domain

import com.esgi.nova.ports.provided.IUserService
import com.esgi.nova.ports.provided.UserDto
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.Inject


class UserService @Inject constructor(private val userPersistence: IUserPersistence) : IUserService {
    init {
        println("HELLO")
    }

    override fun getAllUsers() = userPersistence.getAllUsers()
    override fun getOrPutUser(userDto: UserDto) = userPersistence.getOrPutUser(userDto)
}