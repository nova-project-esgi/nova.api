package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.GameMapper
import com.esgi.nova.adapters.exposed.repositories.GameRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.required.IGamePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class GamePersistence @Inject constructor(
    private val gameRepository: GameRepository,
    private val gameMapper: GameMapper,
    private val dbContext: DatabaseContext
) : IGamePersistence {
    override fun getAll(): Collection<GameDto> = dbContext.connectAndExec {
        gameMapper.toDtos(gameRepository.getAll())
    }

    override fun create(element: GameCmdDto): GameDto? =
        dbContext.connectAndExec { gameMapper.toDto(gameRepository.create(element)) }

    override fun getOne(id: UUID): GameDto? =
        dbContext.connectAndExec { gameRepository.getOne(id)?.let { game -> gameMapper.toDto(game) } }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<GameDto> {
        TODO("Not yet implemented")
    }
}