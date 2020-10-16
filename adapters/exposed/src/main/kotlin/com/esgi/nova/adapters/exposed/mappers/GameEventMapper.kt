package com.esgi.nova.adapters.exposed.mappers

import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.ports.provided.dtos.GameEventDto
import org.mapstruct.Mapper

@Mapper(componentModel = "jsr330", uses = [GameMapper::class, EventMapper::class])
interface GameEventMapper {


    fun toDto(gameEventEntity: GameEventEntity): GameEventDto
}