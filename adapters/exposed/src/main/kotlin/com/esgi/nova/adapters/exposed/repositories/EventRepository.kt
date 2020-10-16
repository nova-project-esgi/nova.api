package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.ports.provided.dtos.EventDto
import org.jetbrains.exposed.sql.transactions.transaction

class EventRepository {
    fun getAll(): List<EventEntity> = transaction { EventEntity.all().toList() }
    fun create(eventEntity: EventDto): EventEntity = EventEntity.new {
        description = eventEntity.description
        isActive = eventEntity.isActive
        title = eventEntity.title
        isDaily = eventEntity.isDaily
    }
}