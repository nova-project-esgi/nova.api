package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.EventMapper
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.repositories.EventRepository
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.required.IEventPersistence
import com.google.inject.Inject
import java.util.*

class EventPersistence @Inject constructor(
    override val repository: EventRepository,
    mapper: EventMapper,
    dbContext: DatabaseContext
) : BasePersistence<UUID, EventCmdDto, EventEntity, EventDto>(repository, mapper, dbContext), IEventPersistence {

    override fun getAllByIds(ids: List<UUID>): Collection<EventDto> =
        dbContext.connectAndExec { mapper.toDtos(repository.getAllByIds(ids).toList()) }

}