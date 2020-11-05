package com.esgi.nova.ports.provided.dtos.choice.queries

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import java.util.*


open class ChoiceDto(
    override var id: UUID,
    var resources: List<ResourceDto>,
    var event: EventDto,
) : IId<UUID>
