package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.UserMapper
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.adapters.exposed.repositories.UserRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.user.UserCmdDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import com.esgi.nova.ports.required.ITotalCollection
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.Inject

class UserPersistence @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val dbContext: DatabaseContext
) : IUserPersistence {

    override fun getAll(): Collection<UserDto> =
        dbContext.connectAndExec { userMapper.toDtos(userRepository.getAll()) }

    override fun create(element: UserCmdDto): UserDto? =
        dbContext.connectAndExec { userMapper.toDto(userRepository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<UserDto> = dbContext.connectAndExec {
        val elements = userRepository.getAllTotal(DatabasePagination(pagination))
        TotalCollection(elements.total, userMapper.toDtos(elements))
    }

    override fun getByName(username: String): UserDto? = dbContext.connectAndExec {
        userMapper.toDto(userRepository.getByName(username) as UserEntity)
    }

    override fun getOrCreate(userDto: UserCmdDto): UserDto? {
        return dbContext.connectAndExec {
            val editedUser = userRepository.getOrCreate(userDto)
            userMapper.toDto(editedUser)
        }
    }

}