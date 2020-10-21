package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.*

@Mapper(
    componentModel = "jsr330",
    uses = [EventMapper::class, UserMapper::class, EntityMapper::class]
)
abstract class GameMapper{

    abstract fun toDto(game: GameEntity?, @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()): GameDto?
    abstract fun toDtos(games: List<GameEntity>, @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()): List<GameDto>

}