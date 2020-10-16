package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.UserDto
import com.esgi.nova.ports.provided.services.IUserService
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.Inject


class UserService @Inject constructor(private val userPersistence: IUserPersistence) : IUserService {
    override fun getAll() = userPersistence.getAll()
    override fun getOrPut(userDto: UserDto) = userPersistence.getOrPut(userDto)
}