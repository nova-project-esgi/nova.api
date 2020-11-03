package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.ResourceMapper
import com.esgi.nova.adapters.exposed.repositories.ResourceRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.required.IResourcePersistence
import com.esgi.nova.ports.required.ITotalCollection
import com.google.inject.Inject
import java.util.*

class ResourcePersistence @Inject constructor(
    private val resourceRepository: ResourceRepository,
    private val resourceMapper: ResourceMapper,
    private val dbContext: DatabaseContext
) : IResourcePersistence {
    override fun getAll(): Collection<ResourceDto> = dbContext.connectAndExec {
        resourceMapper.toDtos(resourceRepository.getAll())
    }

    override fun create(element: ResourceCmdDto): ResourceDto? =
        dbContext.connectAndExec { resourceMapper.toDto(resourceRepository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<ResourceDto> = dbContext.connectAndExec {
        val elements = resourceRepository.getAllTotal(DatabasePagination(pagination))
        TotalCollection(elements.total, resourceMapper.toDtos(elements))
    }

    override fun getOne(id: UUID): ResourceDto? =
        dbContext.connectAndExec { resourceRepository.getOne(id)?.let { resource -> resourceMapper.toDto(resource) } }

}