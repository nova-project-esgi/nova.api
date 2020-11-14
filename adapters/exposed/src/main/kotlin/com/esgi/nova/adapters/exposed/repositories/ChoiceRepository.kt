package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.tables.Choice
import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceRepository : IRepository<UUID, ChoiceCmdDto, ChoiceEntity> {
    override fun getAll(): SizedIterable<ChoiceEntity> = transaction { ChoiceEntity.all() }
    override fun getOne(id: UUID): ChoiceEntity? = transaction { ChoiceEntity[id] }
    override fun create(element: ChoiceCmdDto): ChoiceEntity? = transaction {
        ChoiceEntity.new {
            EventEntity.findById(element.eventId)?.let { eventEntity -> event = eventEntity }
        }
    }
    override fun updateOne(element: ChoiceCmdDto, id: UUID): ChoiceEntity? = transaction {
        getOne(id)?.also { choice ->
            EventEntity.findById(element.eventId)?.let { eventEntity ->
                choice.event = eventEntity
            }
        }
    }
    override fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ChoiceEntity> = transaction {
        val elements = ChoiceEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getAllByEventId(eventId: UUID) = transaction { ChoiceEntity.find { Choice.event eq eventId } }


}