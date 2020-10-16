package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.mappers.GameMapper
import com.esgi.nova.adapters.exposed.repositories.GameRepository
import com.esgi.nova.ports.provided.dtos.GameDto
import com.esgi.nova.ports.required.IGamePersistence
import com.google.inject.Inject

class GamePersistence @Inject constructor(
    private val gameRepository: GameRepository,
    private val gameMapper: GameMapper
) : IGamePersistence {
    override fun getAll(): List<GameDto> = gameRepository.getAll().map { gameMapper.toDto(it) }
}