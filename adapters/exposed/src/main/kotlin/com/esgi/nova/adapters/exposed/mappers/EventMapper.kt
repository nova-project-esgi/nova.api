package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.ports.provided.dtos.event.EventDto
import org.mapstruct.Context
import org.mapstruct.Mapper


@Mapper(componentModel = "jsr330", uses = [GameMapper::class, EntityMapper::class])
interface EventMapper {

    fun toDto(
        event: EventEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): EventDto?

    fun toDtos(
        events: Collection<EventEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): Collection<EventDto>

}