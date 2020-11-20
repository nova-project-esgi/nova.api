package com.esgi.nova.adapters.exposed.repositories.events

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableByIds
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.ports.provided.dtos.event.EventCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class EventRepository: IRepository<UUID, EventCmdDto, EventEntity>, IGetAllIterableByIds<UUID, EventEntity> {
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

    override fun updateOne(element: EventCmdDto, id: UUID): EventEntity? = transaction{
        getOne(id)?.also { event ->
            event.isActive = element.isActive
            event.isDaily = element.isDaily
        }
    }

    override fun getAllByIds(ids: Collection<UUID>): SizedIterable<EventEntity>  = transaction { EventEntity.forIds(ids.toList()) }

}