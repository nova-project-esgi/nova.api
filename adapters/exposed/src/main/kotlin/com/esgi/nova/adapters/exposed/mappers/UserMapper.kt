package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.user.UserDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [EntityMapper::class])
interface UserMapper: IDtoMapper<UserEntity, UserDto> {
//    override fun toDto(
//        srcEntity: UserEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): UserDto?
//
//    override fun toDtos(
//        srcEntities: Collection<UserEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<UserDto>
}