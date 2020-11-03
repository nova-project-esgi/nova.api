package com.esgi.nova.ports.provided.dtos.choice

import java.util.*

class TranslatedChoiceWithResourcesCmdDto(
    eventId: UUID,
    resources: List<ResourceChangeDto>,
    var title: String, var description: String
) : ChoiceWithResourcesCmdDto(eventId, resources) {
}