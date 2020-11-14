package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.ports.provided.dtos.event.EventDto
import org.mapstruct.Context
import org.mapstruct.Mapper


@Mapper(componentModel = "jsr330", uses = [GameMapper::class, EntityMapper::class])
interface EventMapper: IDtoMapper<EventEntity, EventDto> {

//    override fun toDto(
//        srcEntity: EventEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): EventDto?
//
//    override fun toDtos(
//        srcEntities: Collection<EventEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<EventDto>

}