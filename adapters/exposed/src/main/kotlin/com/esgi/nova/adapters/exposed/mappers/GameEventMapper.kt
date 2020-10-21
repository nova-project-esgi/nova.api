package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [GameMapper::class, EventMapper::class, EntityMapper::class])
abstract class GameEventMapper {

    abstract fun toDto(gameEvent: GameEventEntity?, @Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): GameEventDto?
    abstract fun toDtos(gameEvent: List<GameEventEntity>,@Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): List<GameEventDto>
}