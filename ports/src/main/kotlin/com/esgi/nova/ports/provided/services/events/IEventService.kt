package com.esgi.nova.ports.provided.services.events

import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.services.ICrudService
import java.util.*

interface IEventService : ICrudService<UUID, EventCmdDto, EventDto> {

}