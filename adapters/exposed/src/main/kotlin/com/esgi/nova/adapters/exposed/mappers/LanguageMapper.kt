package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.LanguageEntity
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [EntityMapper::class])
interface LanguageMapper {
    fun toDto(
        entity: LanguageEntity?,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext()
    ): LanguageDto?

    fun toDtos(
        entity: List<LanguageEntity>,
        @Context context: CycleAvoidingMappingContext = CycleAvoidingMappingContext(ignoreFirstLevelIterable = false)
    ): List<LanguageDto>
}