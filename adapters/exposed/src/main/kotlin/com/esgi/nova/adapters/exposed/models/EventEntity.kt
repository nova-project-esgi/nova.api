package com.esgi.nova.adapters.exposed.models

import com.esgi.nova.adapters.exposed.tables.Choice
import com.esgi.nova.adapters.exposed.tables.Event
import com.esgi.nova.adapters.exposed.tables.GameEvent
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


class EventEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<EventEntity>(Event)

    private var _games: List<GameEntity>? = null
    private var _choices: List<ChoiceEntity>? = null
    var isDaily by Event.isDaily
    var isActive by Event.isActive
    val choicesIterable by ChoiceEntity referrersOn  Choice.event
    var gamesIterable by GameEntity via GameEvent
    val games
        get(): List<GameEntity> {
            _games = _games ?: gamesIterable.toList()
            return _games!!
        }
    val choices
        get(): List<ChoiceEntity> {
            _choices = _choices ?: choicesIterable.toList()
            return _choices!!
        }
}
