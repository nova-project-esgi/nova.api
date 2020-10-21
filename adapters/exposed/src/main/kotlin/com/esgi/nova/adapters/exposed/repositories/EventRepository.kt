package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.models.GameEntity
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.google.inject.Inject
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class EventRepository @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(query: Query): List<EventEntity> = transaction { EventEntity.all().toList() }
    fun create(eventEntity: EventCmdDto): EventEntity = transaction{
        EventEntity.new {
            description = eventEntity.description
            isActive = eventEntity.isActive
            title = eventEntity.title
            isDaily = eventEntity.isDaily
        }
    }
    fun getOne(id: UUID): EventEntity? = transaction { EventEntity.findById(id) }
}