package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(
    componentModel = "jsr330",
    uses = [ResourceMapper::class, EventMapper::class, EntityMapper::class, GameMapper::class]
)
interface ChoiceMapper: IDtoMapper<ChoiceEntity, ChoiceDto> {

//    override fun toDto(
//        srcEntity: ChoiceEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): ChoiceDto?
//
//    override fun toDtos(
//        srcEntities: Collection<ChoiceEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<ChoiceDto>
}