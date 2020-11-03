package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(
    componentModel = "jsr330",
    uses = [ResourceMapper::class, EventMapper::class, EntityMapper::class, GameMapper::class]
)
interface ChoiceMapper {

    fun toDto(
        choice: ChoiceEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): ChoiceDto?

    fun toDtos(
        choices: Collection<ChoiceEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): Collection<ChoiceDto>
}