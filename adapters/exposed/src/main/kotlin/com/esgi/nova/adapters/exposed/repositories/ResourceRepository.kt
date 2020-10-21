package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.ports.provided.Query
import com.google.inject.Inject
import org.jetbrains.exposed.sql.transactions.transaction

class ResourceRepository   @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(query: Query): List<ResourceEntity> = transaction{ ResourceEntity.all().toList() }
}