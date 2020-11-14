package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ChoiceMapper::class, EventMapper::class, EntityMapper::class])
interface ResourceMapper: IDtoMapper<ResourceEntity, ResourceDto> {
//
//    override fun toDto(
//        srcEntity: ResourceEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): ResourceDto?
//
//    override fun toDtos(
//        srcEntities: Collection<ResourceEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<ResourceDto>

}