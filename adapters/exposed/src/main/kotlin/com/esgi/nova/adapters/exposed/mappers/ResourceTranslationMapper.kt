package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ResourceTranslationEntity
import com.esgi.nova.ports.provided.dtos.resource_translation.ResourceTranslationDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ResourceMapper::class, LanguageMapper::class, EntityMapper::class])
interface ResourceTranslationMapper {
    fun toDto(
        entity: ResourceTranslationEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): ResourceTranslationDto?

    fun toDtos(
        entities: List<ResourceTranslationEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): List<ResourceTranslationDto>
}