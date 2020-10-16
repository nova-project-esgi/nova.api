package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.mappers.EventMapper
import com.esgi.nova.adapters.exposed.repositories.EventRepository
import com.esgi.nova.ports.provided.dtos.EventDto
import com.esgi.nova.ports.required.IEventPersistence
import com.google.inject.Inject

class EventPersistence @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventMapper: EventMapper
) : IEventPersistence {
    override fun getAll(): List<EventDto> = eventRepository.getAll().map { eventMapper.toDto(it) }
    override fun create(event: EventDto) = eventMapper.toDto(eventRepository.create(event))
}