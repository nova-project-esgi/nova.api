package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import org.jetbrains.exposed.sql.transactions.transaction

class ChoiceResourceRepository {
    fun getAll(): List<ChoiceResourceEntity> = transaction { ChoiceResourceEntity.all().toList() }
}