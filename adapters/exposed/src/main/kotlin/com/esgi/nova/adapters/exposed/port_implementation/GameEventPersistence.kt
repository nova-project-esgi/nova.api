package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.mappers.GameEventMapper
import com.esgi.nova.adapters.exposed.repositories.GameEventRepository
import com.esgi.nova.ports.provided.dtos.GameEventDto
import com.esgi.nova.ports.required.IGameEventPersistence
import com.google.inject.Inject

class GameEventPersistence @Inject constructor(
    private val gameEventRepository: GameEventRepository,
    private val gameEventMapper: GameEventMapper
) : IGameEventPersistence {
    override fun getAll(): List<GameEventDto> = gameEventRepository.getAll().map { gameEventMapper.toDto(it) }
}