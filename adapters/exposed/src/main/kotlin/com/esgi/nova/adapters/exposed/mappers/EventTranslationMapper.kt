package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.adapters.exposed.models.EventTranslationEntity
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.event_translation.EventTranslationDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [EventMapper::class, LanguageMapper::class, EntityMapper::class])
interface EventTranslationMapper:IDtoMapper<EventTranslationEntity, EventTranslationDto>  {
//    override fun toDto(
//        srcEntity: EventTranslationEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): EventTranslationDto?
//
//    override fun toDtos(
//        srcEntities: Collection<EventTranslationEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<EventTranslationDto>
}