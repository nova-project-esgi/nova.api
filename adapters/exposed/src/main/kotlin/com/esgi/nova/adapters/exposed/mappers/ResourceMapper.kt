package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ChoiceMapper::class, EventMapper::class, EntityMapper::class])
interface ResourceMapper {

    fun toDto(
        resource: ResourceEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): ResourceDto?

    fun toDtos(
        resources: Collection<ResourceEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): Collection<ResourceDto>

}