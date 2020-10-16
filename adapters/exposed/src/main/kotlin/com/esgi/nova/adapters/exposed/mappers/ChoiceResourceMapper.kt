package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.ports.provided.dtos.ChoiceResourceDto
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ChoiceMapper::class, ResourceMapper::class])
interface ChoiceResourceMapper {
    fun toDto(choiceResourceEntity: ChoiceResourceEntity): ChoiceResourceDto
}