package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.adapters.exposed.tables.GameEvent
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import com.google.inject.Inject
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

public class GameEventRepository @Inject constructor() {
    fun getAll(): List<GameEventEntity> = transaction { GameEventEntity.all().toList() }
    fun create(gameEvent: GameEventCmdDto) = transaction {
        GameEventEntity.new {
            game = GameEntity[gameEvent.gameId]
            event = EventEntity[gameEvent.eventId]
            gameEvent.linkTime?.let { date ->
                linkTime = date
            }
        }
    }

    fun getAllById(id: GameEventsId) =
        transaction {
            GameEventEntity.find { (GameEvent.event eq id.eventId) and (GameEvent.game eq id.gameId) }.toList()
        }

    fun getOne(id: UUID): GameEventEntity? = transaction { GameEventEntity[id] }
}