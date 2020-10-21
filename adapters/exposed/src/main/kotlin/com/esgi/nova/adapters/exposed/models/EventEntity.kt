package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.Event
import com.esgi.nova.adapters.exposed.tables.GameEvent
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


class EventEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<EventEntity>(Event)

    private var _games: List<GameEntity>? = null
    var title by Event.title
    var description by Event.description
    var isDaily by Event.isDaily
    var isActive by Event.isActive
    var gamesIterable by GameEntity via GameEvent
    val games get(): List<GameEntity>{
        _games = _games ?: gamesIterable.toList()
        return _games!!
    }
}