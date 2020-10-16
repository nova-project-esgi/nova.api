package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.dtos.EventDto
import com.esgi.nova.ports.provided.services.IEventService
import com.esgi.nova.ports.required.IEventPersistence
import com.google.inject.Inject

class EventService @Inject constructor(private val eventPersistence: IEventPersistence) : IEventService {
    override fun getAll() = eventPersistence.getAll()
    override fun create(event: EventDto) = eventPersistence.create(event)

}