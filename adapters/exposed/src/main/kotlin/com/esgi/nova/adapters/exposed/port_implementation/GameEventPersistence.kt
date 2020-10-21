package com.esgi.nova.adapters.exposed.port_implementation
import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.GameEventMapper
import com.esgi.nova.adapters.exposed.repositories.GameEventRepository
import com.esgi.nova.ports.provided.ITotalIterable
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventDto
import com.esgi.nova.ports.required.IGameEventPersistence
import com.google.inject.Inject

class GameEventPersistence @Inject constructor(
    private val gameEventRepository: GameEventRepository,
    private val gameEventMapper: GameEventMapper,
    private val dbContext: DatabaseContext
) : IGameEventPersistence {
    override fun getAll(query: Query): List<GameEventDto> =
        dbContext.connectAndExec { gameEventMapper.toDtos(gameEventRepository.getAll(query)) }

    override fun create(element: GameEventCmdDto): GameEventDto?{
        val entity = dbContext.connectAndExec { gameEventRepository.create(element) }
        val dto = dbContext.connectAndExec { gameEventMapper.toDto(entity) }
        return dto
    }


    override fun getAllTotal(query: com.esgi.nova.ports.provided.Query): ITotalIterable<GameEventDto> {
        TODO("Not yet implemented")
    }
}