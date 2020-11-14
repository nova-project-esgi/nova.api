package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class ResourceRepository : IRepository<UUID, ResourceCmdDto, ResourceEntity> {

    override fun getAll() = transaction { ResourceEntity.all() }
    override fun create(element: ResourceCmdDto) = transaction {
        ResourceEntity.new {

        }
    }

    override fun getOne(id: UUID) = transaction { ResourceEntity.findById(id) }
    override fun getAllTotal(pagination: DatabasePagination) = transaction {
        val elements = ResourceEntity.all()
        TotalCollection(elements.count(), elements.limit(pagination.size.toInt(), pagination.offset).toList())
    }

    override fun updateOne(element: ResourceCmdDto, id: UUID): ResourceEntity? = transaction {
        ResourceEntity.findById(id)?.also {

        }
    }
}
