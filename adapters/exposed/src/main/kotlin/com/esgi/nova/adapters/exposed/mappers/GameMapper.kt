package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(
    componentModel = "jsr330",
    uses = [EventMapper::class, UserMapper::class, EntityMapper::class]
)
interface GameMapper: IDtoMapper<GameEntity, GameDto> {

//    override fun toDto(
//        srcEntity: GameEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): GameDto?
//
//    override fun toDtos(
//        srcEntities: Collection<GameEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<GameDto>

}