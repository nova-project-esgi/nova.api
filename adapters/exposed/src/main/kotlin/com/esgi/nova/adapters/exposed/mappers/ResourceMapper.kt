package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.ports.provided.dtos.ChoiceDto
import com.esgi.nova.ports.provided.dtos.ResourceDto
import org.jetbrains.exposed.sql.SizedIterable
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [ChoiceMapper::class, EventMapper::class])
interface ResourceMapper {

    fun toDto(resourceEntity: ResourceEntity): ResourceDto
    fun map(gameEntities: SizedIterable<ChoiceEntity>): Iterable<ChoiceDto>

}