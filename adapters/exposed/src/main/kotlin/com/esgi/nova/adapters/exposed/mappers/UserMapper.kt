package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.ports.provided.dtos.user.UserDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [EntityMapper::class])
interface UserMapper {
    fun toDto(
        user: UserEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): UserDto?

    fun toDtos(
        users: Collection<UserEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): Collection<UserDto>
}