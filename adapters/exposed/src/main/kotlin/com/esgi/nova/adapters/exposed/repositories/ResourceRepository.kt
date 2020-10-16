package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.ResourceEntity
import org.jetbrains.exposed.sql.transactions.transaction

class ResourceRepository {
    fun getAll(): List<ResourceEntity> = transaction { ResourceEntity.all().toList() }
}