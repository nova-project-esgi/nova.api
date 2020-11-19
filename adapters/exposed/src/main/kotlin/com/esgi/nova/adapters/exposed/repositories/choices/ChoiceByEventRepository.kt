package com.esgi.nova.adapters.exposed.repositories.choices

import com.esgi.nova.adapters.exposed.domain.IGetAllIterableById
import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.adapters.exposed.models.EventEntity
import com.esgi.nova.adapters.exposed.tables.Choice
import com.esgi.nova.ports.common.IGetAllById
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ChoiceByEventRepository: IGetAllIterableById<UUID, ChoiceEntity>{
    override fun getAllById(id: UUID) = transaction { ChoiceEntity.find { Choice.event eq id } }
}