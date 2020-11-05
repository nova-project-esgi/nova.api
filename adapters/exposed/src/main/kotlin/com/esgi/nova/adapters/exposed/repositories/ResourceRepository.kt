package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.google.inject.Inject
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ResourceRepository @Inject constructor(private val dbContext: DatabaseContext) {
    fun getAll(): List<ResourceEntity> = transaction { ResourceEntity.all().toList() }
    fun create(resource: ResourceCmdDto) = transaction {
        ResourceEntity.new {

        }
    }

    fun getOne(id: UUID) = transaction { ResourceEntity.findById(id) }
    fun getAllTotal(pagination: DatabasePagination) = transaction {
        val elements = ResourceEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    fun updateOne(id: UUID, resource: ResourceCmdDto) = transaction {
        ResourceEntity.findById(id)?.also {

        }
    }
}