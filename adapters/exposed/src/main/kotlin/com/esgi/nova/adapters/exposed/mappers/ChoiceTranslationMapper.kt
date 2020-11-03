package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ChoiceMapper::class, LanguageMapper::class, EntityMapper::class])
interface ChoiceTranslationMapper {
    fun toDto(
        entity: ChoiceTranslationEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): ChoiceTranslationDto?

    fun toDtos(
        entities: List<ChoiceTranslationEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): List<ChoiceTranslationDto>
}