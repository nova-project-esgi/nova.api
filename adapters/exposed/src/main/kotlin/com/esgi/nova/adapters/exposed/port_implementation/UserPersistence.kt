package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.mappers.UserMapper
import com.esgi.nova.adapters.exposed.repositories.UserRepository
import com.esgi.nova.ports.provided.dtos.UserDto
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.Inject

class UserPersistence @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : IUserPersistence {

    override fun getAll(): List<UserDto> = userRepository.getAll().map { userMapper.toDto(it) }
    override fun getOrPut(userDto: UserDto): UserDto {
        val editedUser = userRepository.getOrPut(userDto)
        return userMapper.toDto(editedUser)
    }

}