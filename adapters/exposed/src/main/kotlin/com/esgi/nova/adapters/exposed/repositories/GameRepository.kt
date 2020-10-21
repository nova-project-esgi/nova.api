package com.esgi.nova.adapters.exposed.repositories
import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.google.inject.Inject
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class GameRepository  @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(query: Query): List<GameEntity> = transaction { GameEntity.all().toList() }
    fun getOne(id: UUID) = transaction{ GameEntity[id] }
    fun create(game: GameCmdDto) = transaction {
        GameEntity.new {
            user = UserEntity[game.userId]
            score = game.score
            startDate = game.startDate
        }
    }
}