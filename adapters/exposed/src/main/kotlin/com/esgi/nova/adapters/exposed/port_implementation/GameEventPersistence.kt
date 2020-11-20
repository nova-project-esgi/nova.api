package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.GameEventMapper
import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.repositories.game_events.GameEventRepository
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import com.esgi.nova.ports.required.IGameEventPersistence
import com.google.inject.Inject
import java.util.*

class GameEventPersistence @Inject constructor(
        override val repository: GameEventRepository,
        mapper: GameEventMapper,
        dbContext: DatabaseContext
) : BasePersistence<UUID, GameEventCmdDto, GameEventEntity, GameEventDto>(repository, mapper, dbContext),
    IGameEventPersistence {

    override fun getAllFiltered(filter: GameEventsId): Collection<GameEventDto> =
        dbContext.connectAndExec { mapper.toDtos(repository.getAllById(filter)) }
}