package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class EventRepository @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(): Collection<EventEntity> = transaction { EventEntity.all().toList() }
    fun getAllTotal(pagination: DatabasePagination): ITotalCollection<EventEntity> = transaction {
        val elements = EventEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun create(eventEntity: EventCmdDto): EventEntity = transaction {
        EventEntity.new {
            isActive = eventEntity.isActive
            isDaily = eventEntity.isDaily
        }
    }

    fun getOne(id: UUID): EventEntity? = transaction { EventEntity[id] }

    fun getAllByIds(ids: List<UUID>) = transaction { EventEntity.forIds(ids) }
    fun updateOne(id: UUID, element: EventCmdDto) = transaction{
        getOne(id)?.also { event ->
            event.isActive = element.isActive
            event.isDaily = element.isDaily
        }
    }
}