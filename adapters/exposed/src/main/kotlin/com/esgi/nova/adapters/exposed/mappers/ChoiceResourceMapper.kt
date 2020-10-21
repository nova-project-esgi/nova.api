package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.Context
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ChoiceMapper::class, ResourceMapper::class])
abstract class ChoiceResourceMapper{
    abstract fun toDto(choiceResource: ChoiceResourceEntity?,@Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): ChoiceResourceDto?
    abstract fun toDtos(choiceResources: List<ChoiceResourceEntity>,@Context context:CycleAvoidingMappingContext = CycleAvoidingMappingContext()): List<ChoiceResourceDto>
}