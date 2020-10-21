package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.Game
import com.esgi.nova.adapters.exposed.tables.GameEvent
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class GameEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<GameEntity>(Game)
    private var _events: List<EventEntity>? = null
    var user by UserEntity referencedOn Game.user
    var startDate by Game.startDate
    var score by Game.score
    var eventsIterable by EventEntity via GameEvent
    val events get(): List<EventEntity>{
        _events = _events ?: eventsIterable.toList()
        return _events!!
    }
}