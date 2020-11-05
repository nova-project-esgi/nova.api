package com.esgi.nova.ports.provided.dtos.choice.commands

import com.esgi.nova.ports.provided.dtos.choice.queries.ResourceChangeDto
import java.util.*

class TranslatedChoiceWithResourcesCmdDto(
    eventId: UUID,
    resources: List<ResourceChangeDto>,
    var title: String, var description: String
) : ChoiceWithResourcesCmdDto(eventId, resources) {
}