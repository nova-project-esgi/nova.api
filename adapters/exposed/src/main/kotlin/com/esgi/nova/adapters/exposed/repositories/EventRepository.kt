package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class EventRepository: IRepository<UUID, EventCmdDto, EventEntity> {
    override fun getAll() = transaction { EventEntity.all()}
    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<EventEntity> = transaction {
        val elements = EventEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun create(element: EventCmdDto): EventEntity = transaction {
        EventEntity.new {
            isActive = element.isActive
            isDaily = element.isDaily
        }
    }

    override fun getOne(id: UUID): EventEntity? = transaction { EventEntity[id] }

    fun getAllByIds(ids: List<UUID>) = transaction { EventEntity.forIds(ids) }
    override fun updateOne(element: EventCmdDto, id: UUID): EventEntity? = transaction{
        getOne(id)?.also { event ->
            event.isActive = element.isActive
            event.isDaily = element.isDaily
        }
    }
}