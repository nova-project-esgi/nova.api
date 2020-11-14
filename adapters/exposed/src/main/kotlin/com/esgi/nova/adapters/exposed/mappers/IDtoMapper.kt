package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import org.mapstruct.Context

interface IDtoMapper<Entity, Dto> {
    fun toDto(
        srcEntity: Entity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): Dto?

    fun toDtos(
        srcEntities: Collection<Entity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): Collection<Dto>
}