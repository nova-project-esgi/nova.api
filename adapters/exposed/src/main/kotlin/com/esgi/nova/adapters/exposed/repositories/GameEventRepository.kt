package com.esgi.nova.adapters.exposed.repositories
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.adapters.exposed.models.GameEventEntity
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.game_event.GameEventCmdDto
import com.google.inject.Inject
import org.jetbrains.exposed.sql.transactions.transaction

public class GameEventRepository @Inject constructor(){
    fun getAll(query: Query): List<GameEventEntity> = transaction { GameEventEntity.all().toList()}
    fun create(gameEvent: GameEventCmdDto) = transaction {
        GameEventEntity.new {
            game = GameEntity[gameEvent.gameId]
            event = EventEntity[gameEvent.eventId]
            gameEvent.linkTime?.let{date->
                linkTime = date
            }
        }
    }
}