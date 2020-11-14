package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ResourceMapper::class, ChoiceMapper::class, EntityMapper::class])
interface ChoiceResourceMapper: IDtoMapper<ChoiceResourceEntity, ChoiceResourceDto>  {
//    override fun toDto(
//        srcEntity: ChoiceResourceEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): ChoiceResourceDto?
//
//    override fun toDtos(
//        srcEntities: Collection<ChoiceResourceEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<ChoiceResourceDto>
}