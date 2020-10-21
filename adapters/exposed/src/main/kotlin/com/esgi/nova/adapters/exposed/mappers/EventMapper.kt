package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.ports.provided.dtos.event.EventDto
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.*
import java.util.*


@Mapper(componentModel = "jsr330", uses = [GameMapper::class, EntityMapper::class])
abstract class EventMapper {

    abstract fun toDto(event: EventEntity?, @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()): EventDto?
    abstract fun toDtos(events: List<EventEntity>,@Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()): List<EventDto>

}