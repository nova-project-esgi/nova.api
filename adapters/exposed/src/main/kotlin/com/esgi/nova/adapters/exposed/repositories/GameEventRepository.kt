package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.adapters.exposed.tables.GameEvent
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class GameEventRepository : IRepository<UUID, GameEventCmdDto, GameEventEntity> {
    override fun getAll(): SizedIterable<GameEventEntity> = transaction { GameEventEntity.all()}
    override fun create(element: GameEventCmdDto) = transaction {
        GameEventEntity.new {
            GameEntity.findById(element.gameId)?.let { gameEntity -> game = gameEntity }
            EventEntity.findById(element.eventId)?.let { eventEntity -> event = eventEntity }
            element.linkTime?.let { date ->
                linkTime = date
            }
        }
    }

    fun getAllById(id: GameEventsId) =
        transaction {
            GameEventEntity.find { (GameEvent.event eq id.eventId) and (GameEvent.game eq id.gameId) }.toList()
        }

    override fun getOne(id: UUID): GameEventEntity? = transaction { GameEventEntity.findById(id) }

    override fun updateOne(element: GameEventCmdDto, id: UUID): GameEventEntity? = transaction {
        getOne(id)?.also { gameEventEntity ->
            element.linkTime?.let { time -> gameEventEntity.linkTime = time }
            EventEntity.findById(element.eventId)?.let { eventEntity -> gameEventEntity.event = eventEntity }
            GameEntity.findById(element.gameId)?.let { gameEntity -> gameEventEntity.game = gameEntity }
        }
    }

    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<GameEventEntity> {
        TODO("Not yet implemented")
    }
}