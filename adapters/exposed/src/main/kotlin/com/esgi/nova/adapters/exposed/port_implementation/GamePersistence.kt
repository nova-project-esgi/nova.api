package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.GameMapper
import com.esgi.nova.adapters.exposed.repositories.GameRepository
import com.esgi.nova.ports.provided.ITotalIterable
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.required.IGamePersistence
import com.google.inject.Inject
import java.util.*

class GamePersistence @Inject constructor(
    private val gameRepository: GameRepository,
    private val gameMapper: GameMapper,
    private val dbContext: DatabaseContext
) : IGamePersistence {
    override fun getAll(query: Query): List<GameDto> = dbContext.connectAndExec {
        gameMapper.toDtos(gameRepository.getAll(query))}

        override fun create(element: GameCmdDto): GameDto? =
            dbContext.connectAndExec { gameMapper.toDto(gameRepository.create(element)) }

        override fun getOne(id: UUID): GameDto? =
            dbContext.connectAndExec { gameMapper.toDto(gameRepository.getOne(id)) }

        override fun getAllTotal(query: Query): ITotalIterable<GameDto> {
            TODO("Not yet implemented")
        }
    }