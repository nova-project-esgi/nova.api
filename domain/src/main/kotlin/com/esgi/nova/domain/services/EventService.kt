package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.services.IEventService
import com.esgi.nova.ports.required.IEventPersistence
import com.google.inject.Inject
import java.util.*

class EventService @Inject constructor(
    override val persistence: IEventPersistence,
) : BaseService<UUID, EventCmdDto, EventDto>(persistence), IEventService {


}