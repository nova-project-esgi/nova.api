package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ChoiceMapper::class, EventMapper::class, EntityMapper::class])
abstract class ResourceMapper {

    abstract fun toDto(resource: ResourceEntity?, @Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): ResourceDto?
    abstract fun toDtos(resources: List<ResourceEntity>, @Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): List<ResourceDto>

}