package com.esgi.nova.adapters.exposed.repositories.game_events

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction

abstract class BaseGameEventRepository<ID> :
        IRepository<ID, GameEventCmdDto, GameEventEntity>
{
    override fun getAll(): SizedIterable<GameEventEntity> = transaction { GameEventEntity.all() }
    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<GameEventEntity> = transaction {
        val elements = GameEventEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }
    override fun create(element: GameEventCmdDto): GameEventEntity? = transaction {
        GameEventEntity.new {
            GameEntity.findById(element.gameId)?.let { gameEntity -> game = gameEntity }
            EventEntity.findById(element.eventId)?.let { eventEntity -> event = eventEntity }
            element.linkTime?.let { date ->
                linkTime = date
            }
        }
    }
    override fun updateOne(element: GameEventCmdDto, id: ID): GameEventEntity? =
            transaction {
                getOne(id)?.also { gameEventEntity ->
                    element.linkTime?.let { time -> gameEventEntity.linkTime = time }
                    EventEntity.findById(element.eventId)?.let { eventEntity -> gameEventEntity.event = eventEntity }
                    GameEntity.findById(element.gameId)?.let { gameEntity -> gameEventEntity.game = gameEntity }
                }
            }
}