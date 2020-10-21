package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.required.ICreate
import com.esgi.nova.ports.required.IGetAll
import com.esgi.nova.ports.required.IGetOne
import java.util.*

interface IEventService: IGetAll<EventDto>, ICreate<EventCmdDto,EventDto>, IGetOne<UUID, EventDto> {
}