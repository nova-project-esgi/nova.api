package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.services.IEventService
import com.esgi.nova.ports.required.IEventPersistence
import com.google.inject.Inject
import java.util.*

class EventService @Inject constructor(private val eventPersistence: IEventPersistence) : IEventService {
    override fun getAll(query: Query) = eventPersistence.getAll(query)
    override fun create(element: EventCmdDto) = eventPersistence.create(element)
    override fun getOne(id: UUID): EventDto? = eventPersistence.getOne(id)

}