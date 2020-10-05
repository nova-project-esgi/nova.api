package models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.GameEvent
import java.util.*

class GameEventEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object: UUIDEntityClass<GameEventEntity>(GameEvent)
    var game by GameEntity referencedOn GameEvent.game
    var event by EventEntity referencedOn GameEvent.event
    var linkTime by GameEvent.linkTime
}