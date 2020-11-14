package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceTranslationEntity
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ChoiceMapper::class, LanguageMapper::class, EntityMapper::class])
interface ChoiceTranslationMapper: IDtoMapper<ChoiceTranslationEntity, ChoiceTranslationDto>  {
//    override fun toDto(
//        srcEntity: ChoiceTranslationEntity?,
//        @Context context: CycleAvoidingMappingContext
//    ): ChoiceTranslationDto?
//
//    override fun toDtos(
//        srcEntities: Collection<ChoiceTranslationEntity>,
//        @Context context: CycleAvoidingMappingContext
//    ): Collection<ChoiceTranslationDto>
}