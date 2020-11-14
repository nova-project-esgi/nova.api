package com.esgi.nova.ports.provided.dtos.choice.queries

import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import java.util.*

class ChoiceWithResourceDto(id: UUID, resources: List<ResourceDto>, event: EventDto) :
    ChoiceDto(id, resources, event) {
}