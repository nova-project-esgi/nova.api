package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [GameMapper::class, EventMapper::class, EntityMapper::class])
interface GameEventMapper: IDtoMapper<GameEventEntity, GameEventDto>   {

//    override fun toDto(
//        srcEntity: GameEventEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): GameEventDto?
//
//    override fun toDtos(
//        srcEntities: Collection<GameEventEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<GameEventDto>
}