package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import java.util.*

interface IEventPersistence :
    ICrudPersistence<UUID, EventCmdDto, EventDto> {
    fun getAllByIds(ids: List<UUID>): Collection<EventDto>
}