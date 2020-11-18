package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.UserMapper
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.adapters.exposed.repositories.UserRepository
import com.esgi.nova.ports.provided.dtos.user.UserRegisterCmdDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import com.esgi.nova.ports.required.IUserPersistence
import com.google.inject.Inject
import java.util.*

class UserPersistence @Inject constructor(
        override val repository: UserRepository,
        mapper: UserMapper,
        dbContext: DatabaseContext
) : BasePersistence<UUID, UserRegisterCmdDto, UserEntity, UserDto>(repository, mapper, dbContext), IUserPersistence {

    override fun getByName(username: String): UserDto? = dbContext.connectAndExec {
        repository.getByName(username)?.let { user ->
            mapper.toDto(user)
        }
    }

    override fun getByUsernameAndPassword(username: String, password: String): UserDto? = dbContext.connectAndExec {
        repository.getByUsernameAndPassword(username, password)?.let { user -> mapper.toDto(user) }
    }

//    override fun getOrCreate(userRegisterDto: UserRegisterCmdDto): UserDto? {
//        return dbContext.connectAndExec {
//            val editedUser = repository.getOrCreate(userRegisterDto)
//            mapper.toDto(editedUser)
//        }
//    }

}