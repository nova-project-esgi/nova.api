package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.GameMapper
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.repositories.GameRepository
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.required.IGamePersistence
import com.google.inject.Inject
import java.util.*

class GamePersistence @Inject constructor(
    override val repository: GameRepository,
    mapper: GameMapper,
    dbContext: DatabaseContext
) : BasePersistence<UUID, GameCmdDto, GameEntity, GameDto>(repository, mapper, dbContext), IGamePersistence {
}