package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.models.UserEntity
import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.game.GameCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class GameRepository: IRepository<UUID, GameCmdDto, GameEntity>  {
    override fun getAll() = transaction { GameEntity.all() }
    override fun getOne(id: UUID) = transaction { GameEntity.findById(id) }
    override fun create(element: GameCmdDto) = transaction {
        GameEntity.new {
            UserEntity.findById(element.userId)?.let { userEntity -> user = userEntity  }
            score = element.score
            startDate = element.startDate
        }
    }

    override fun updateOne(element: GameCmdDto, id: UUID): GameEntity? = transaction {
        getOne(id)?.also { gameEntity ->
            UserEntity.findById(element.userId)?.let {userEntity -> gameEntity.user = userEntity  }
            gameEntity.startDate = element.startDate
            gameEntity.score = element.score
        }
    }

    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<GameEntity> {
        TODO("Not yet implemented")
    }
}