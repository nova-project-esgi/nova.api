package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.ports.provided.dtos.user.UserDto
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [EntityMapper::class])
abstract class UserMapper {
    abstract fun toDto(user: UserEntity?,@Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): UserDto?
    abstract fun toDtos(users: List<UserEntity>,@Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): List<UserDto>
}