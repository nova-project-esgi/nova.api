package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import org.jetbrains.exposed.sql.transactions.transaction

class ChoiceRepository {
    fun getAll(): List<ChoiceEntity> = transaction { ChoiceEntity.all().toList() }
}