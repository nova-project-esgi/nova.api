package models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.Event
import tables.GameEvent
import java.util.*


class EventEntity(id: EntityID<UUID>): UUIDEntity(id) {
    companion object : UUIDEntityClass<EventEntity>(Event)
    var title by Event.title
    var description by Event.description
    var isDaily by Event.isDaily
    var isActive by Event.isActive
    var games by GameEntity via GameEvent
}