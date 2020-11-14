package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.adapters.exposed.models.ResourceTranslationEntity
import com.esgi.nova.adapters.exposed.tables.ResourceTranslation
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ResourceMapper::class, LanguageMapper::class, EntityMapper::class])
interface ResourceTranslationMapper: IDtoMapper<ResourceTranslationEntity, ResourceTranslationDto> {
//    override fun toDto(
//        srcEntity: ResourceTranslationEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): ResourceTranslationDto?
//
//    override fun toDtos(
//        srcEntities: Collection<ResourceTranslationEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<ResourceTranslationDto>
}