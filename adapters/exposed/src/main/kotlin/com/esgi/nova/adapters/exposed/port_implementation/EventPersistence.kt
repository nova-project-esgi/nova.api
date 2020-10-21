package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.EventMapper
import com.esgi.nova.adapters.exposed.repositories.EventRepository
import com.esgi.nova.ports.provided.ITotalIterable
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.required.IEventPersistence
import com.google.inject.Inject
import java.util.*

class EventPersistence @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventMapper: EventMapper,
    private val dbContext: DatabaseContext
) : IEventPersistence {
    override fun getAll(query: Query): List<EventDto> =
        dbContext.connectAndExec { eventMapper.toDtos(eventRepository.getAll(query)) }

    override fun create(eventCmdDto: EventCmdDto) =
        dbContext.connectAndExec { eventMapper.toDto(eventRepository.create(eventCmdDto)) }

    override fun getAllTotal(query: Query): ITotalIterable<EventDto> {
        TODO("Not yet implemented")
    }

    override fun getOne(id: UUID): EventDto? =
        dbContext.connectAndExec { eventMapper.toDto(eventRepository.getOne(id)) }
}