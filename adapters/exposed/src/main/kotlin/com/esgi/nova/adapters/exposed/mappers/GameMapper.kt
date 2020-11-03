package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.ports.provided.dtos.game.GameDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(
    componentModel = "jsr330",
    uses = [EventMapper::class, UserMapper::class, EntityMapper::class]
)
interface GameMapper {

    fun toDto(
        game: GameEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): GameDto?

    fun toDtos(
        games: Collection<GameEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): Collection<GameDto>

}