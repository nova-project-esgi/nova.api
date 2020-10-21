package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import java.util.*

interface IEventPersistence: IGetAll<EventDto>, ICreate<EventCmdDto, EventDto>,
    IGetAllTotal<EventDto>,
IGetOne<UUID,EventDto> {
}