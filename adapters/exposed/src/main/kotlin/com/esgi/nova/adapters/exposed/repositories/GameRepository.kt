package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.google.inject.Inject
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class GameRepository @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(): List<GameEntity> = transaction { GameEntity.all().toList() }
    fun getOne(id: UUID) = transaction { GameEntity.findById(id) }
    fun create(game: GameCmdDto) = transaction {
        GameEntity.new {
            UserEntity.findById(game.userId)?.let { userEntity -> user = userEntity  }
            score = game.score
            startDate = game.startDate
        }
    }

    fun updateOne(id: UUID, element: GameCmdDto) = transaction {
        getOne(id)?.also { gameEntity ->
            UserEntity.findById(element.userId)?.let {userEntity -> gameEntity.user = userEntity  }
            gameEntity.startDate = element.startDate
            gameEntity.score = element.score
        }
    }
}