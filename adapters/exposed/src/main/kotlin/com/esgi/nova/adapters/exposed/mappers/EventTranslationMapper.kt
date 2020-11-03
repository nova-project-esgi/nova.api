package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [EventMapper::class, LanguageMapper::class, EntityMapper::class])
interface EventTranslationMapper {
    fun toDto(
        entity: EventTranslationEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): EventTranslationDto?

    fun toDtos(
        entities: List<EventTranslationEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): List<EventTranslationDto>
}