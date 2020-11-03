package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [GameMapper::class, EventMapper::class, EntityMapper::class])
interface GameEventMapper {

    fun toDto(
        gameEvent: GameEventEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): GameEventDto?

    fun toDtos(
        gameEvent: Collection<GameEventEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): Collection<GameEventDto>
}