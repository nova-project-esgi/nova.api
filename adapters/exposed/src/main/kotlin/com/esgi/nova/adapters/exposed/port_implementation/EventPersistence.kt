package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.EventMapper
import com.esgi.nova.adapters.exposed.repositories.EventRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.required.IEventPersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class EventPersistence @Inject constructor(
    private val eventRepository: EventRepository,
    private val eventMapper: EventMapper,
    private val dbContext: DatabaseContext
) : IEventPersistence {
    override fun getAll(): Collection<EventDto> =
        dbContext.connectAndExec { eventMapper.toDtos(eventRepository.getAll()) }

    override fun create(element: EventCmdDto) =
        dbContext.connectAndExec { eventMapper.toDto(eventRepository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<EventDto> {
        return dbContext.connectAndExec {
            val totalCollection = eventRepository.getAllTotal(DatabasePagination(pagination))
            TotalCollection(
                totalCollection.total,
                eventMapper.toDtos(
                    totalCollection
                )
            )
        }
    }

    override fun getAllByIds(ids: List<UUID>): Collection<EventDto> =
        dbContext.connectAndExec { eventMapper.toDtos(eventRepository.getAllByIds(ids).toList()) }

    override fun getOne(id: UUID): EventDto? =
        dbContext.connectAndExec { eventRepository.getOne(id)?.let { event -> eventMapper.toDto(event) } }

    override fun updateOne(element: EventCmdDto, id: UUID): EventDto? = dbContext.connectAndExec{
        eventRepository.updateOne(id, element).let { event -> eventMapper.toDto(event)  }
    }
}