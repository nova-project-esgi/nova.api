package com.esgi.nova.ports.provided.dtos.choice

import java.util.*

open class ChoiceWithResourcesCmdDto(
    eventId: UUID,
    var resources: List<ResourceChangeDto>
) :
    ChoiceCmdDto(eventId) {
}