package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.GameEventMapper
import com.esgi.nova.adapters.exposed.repositories.GameEventRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import com.esgi.nova.ports.required.IGameEventPersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class GameEventPersistence @Inject constructor(
    private val gameEventRepository: GameEventRepository,
    private val gameEventMapper: GameEventMapper,
    private val dbContext: DatabaseContext
) : IGameEventPersistence {
    override fun getAll(): Collection<GameEventDto> =
        dbContext.connectAndExec { gameEventMapper.toDtos(gameEventRepository.getAll()) }

    override fun create(element: GameEventCmdDto): GameEventDto? {
        val entity = dbContext.connectAndExec { gameEventRepository.create(element) }
        val dto = dbContext.connectAndExec { gameEventMapper.toDto(entity) }
        return dto
    }


    override fun getAllTotal(pagination: IPagination): ITotalCollection<GameEventDto> {
        TODO("Not yet implemented")
    }

    override fun getAllFiltered(filter: GameEventsId): Collection<GameEventDto> =
        dbContext.connectAndExec { gameEventMapper.toDtos(gameEventRepository.getAllById(filter)) }

    override fun getOne(id: UUID): GameEventDto? = dbContext.connectAndExec {
        gameEventRepository.getOne(id)?.let { gameEvent -> gameEventMapper.toDto(gameEvent) }
    }

    override fun updateOne(element: GameEventCmdDto, id: UUID): GameEventDto? = dbContext.connectAndExec {
        gameEventRepository.updateOne(id, element)?.let { choice -> gameEventMapper.toDto(choice) }
    }
}