package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.UserMapper
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.adapters.exposed.repositories.UserRepository
import com.esgi.nova.ports.provided.ITotalIterable
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.user.UserCmdDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.Inject

class UserPersistence @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val dbContext: DatabaseContext
) : IUserPersistence {

    override fun getAll(query: Query): List<UserDto> =
        dbContext.connectAndExec { userMapper.toDtos(userRepository.getAll(query)) }

    override fun create(element: UserCmdDto): UserDto? {
        TODO("Not yet implemented")
    }

    override fun getAllTotal(query: Query): ITotalIterable<UserDto> {
        TODO("Not yet implemented")
    }

    override fun getByName(username: String): UserDto? = dbContext.connectAndExec {
        userMapper.toDto(userRepository.getByName(username) as UserEntity)
    }

    override fun getOrCreate(userDto: UserCmdDto): UserDto? {
        return  dbContext.connectAndExec {
            val editedUser = userRepository.getOrCreate(userDto)
            userMapper.toDto(editedUser)
        }
    }

}