package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(
    componentModel = "jsr330",
    uses = [ResourceMapper::class, EventMapper::class, EntityMapper::class, GameMapper::class]
)
abstract class ChoiceMapper() {

    abstract fun toDto(choice: ChoiceEntity?,@Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): ChoiceDto?
    abstract fun toDtos(choices: List<ChoiceEntity>,@Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): List<ChoiceDto>
}