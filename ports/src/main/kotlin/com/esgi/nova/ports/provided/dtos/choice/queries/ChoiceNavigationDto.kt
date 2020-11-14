package com.esgi.nova.ports.provided.dtos.choice.queries

import com.esgi.nova.ports.provided.dtos.IId
import java.util.*

class ChoiceNavigationDto(
    override var id: UUID,
    var resourceIds: List<UUID>,
    var eventId: UUID,
) : IId<UUID> {
}