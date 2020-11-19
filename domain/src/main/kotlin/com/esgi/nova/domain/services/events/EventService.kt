package com.esgi.nova.domain.services.events

import com.esgi.nova.domain.services.service.BaseService
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.services.events.IEventService
import com.esgi.nova.ports.required.IEventPersistence
import com.google.inject.Inject
import java.util.*

class EventService @Inject constructor(
    override val persistence: IEventPersistence,
) : BaseService<UUID, EventCmdDto, EventDto>(persistence), IEventService {


}