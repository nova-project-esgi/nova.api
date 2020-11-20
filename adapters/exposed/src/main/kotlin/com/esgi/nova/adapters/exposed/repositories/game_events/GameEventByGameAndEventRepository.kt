package com.esgi.nova.adapters.exposed.repositories.game_events

import com.esgi.nova.adapters.exposed.domain.IGetAllIterableById
import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.adapters.exposed.tables.GameEvent
import com.esgi.nova.ports.provided.dtos.game_event.GameEventsId
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class GameEventByGameAndEventRepository: IGetAllIterableById<GameEventsId, GameEventEntity> {
    override fun getAllById(id: GameEventsId): SizedIterable<GameEventEntity> = transaction {
        GameEventEntity.find { (GameEvent.event eq id.eventId) and (GameEvent.game eq id.gameId) }
    }
}