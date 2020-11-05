package com.esgi.nova.ports.provided.dtos.choice.commands

import com.esgi.nova.ports.provided.dtos.choice.queries.ResourceChangeDto
import java.util.*

open class ChoiceWithResourcesCmdDto(
    eventId: UUID,
    var resources: List<ResourceChangeDto>
) :
    ChoiceCmdDto(eventId) {
}