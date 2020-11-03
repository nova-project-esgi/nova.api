package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.tables.Choice
import com.esgi.nova.ports.provided.dtos.choice.ChoiceCmdDto
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceRepository {
    fun getAll(): SizedIterable<ChoiceEntity> = transaction { ChoiceEntity.all() }
    fun getOne(id: UUID): ChoiceEntity? = transaction { ChoiceEntity[id] }
    fun create(choice: ChoiceCmdDto): ChoiceEntity? = transaction {
        ChoiceEntity.new {
            event = EventEntity[choice.eventId]
        }
    }

    fun getAllTotal(pagination: DatabasePagination): ITotalCollection<ChoiceEntity> {
        val elements = ChoiceEntity.all()
        return TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun getAllByEventId(eventId: UUID) = transaction { ChoiceEntity.find { Choice.event eq eventId } }

}