package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [EntityMapper::class])
interface LanguageMapper: IDtoMapper<LanguageEntity, LanguageDto> {
//    override fun toDto(
//        srcEntity: LanguageEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): LanguageDto?
//
//    override fun toDtos(
//        srcEntities: Collection<LanguageEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<LanguageDto>
}