package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ResourceMapper::class, ChoiceMapper::class, EntityMapper::class])
interface ChoiceResourceMapper {
    fun toDto(
        choiceResource: ChoiceResourceEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): ChoiceResourceDto?

    fun toDtos(
        choiceResources: Collection<ChoiceResourceEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): Collection<ChoiceResourceDto>
}