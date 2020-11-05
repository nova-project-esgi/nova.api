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
    private val eventPersistence: IEventPersistence,
) : IEventService {


    override fun getAll() = eventPersistence.getAll()
    override fun create(element: EventCmdDto) = eventPersistence.create(element)
    override fun getOne(id: UUID): EventDto? = eventPersistence.getOne(id)
    override fun getPage(pagination: IPagination): IPage<EventDto> =
        eventPersistence.getAllTotal(pagination).toStaticPage(pagination)

    override fun updateOne(element: EventCmdDto, id: UUID): EventDto? = eventPersistence.updateOne(element, id)


}