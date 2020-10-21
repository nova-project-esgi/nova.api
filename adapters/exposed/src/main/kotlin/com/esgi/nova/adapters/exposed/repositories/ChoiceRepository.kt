package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.ports.provided.Query
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction

class ChoiceRepository {
    fun getAll(query: Query): SizedIterable<ChoiceEntity> = transaction { ChoiceEntity.all()}
}