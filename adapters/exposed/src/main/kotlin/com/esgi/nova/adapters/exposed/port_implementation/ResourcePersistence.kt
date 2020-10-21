package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ResourceMapper
import com.esgi.nova.adapters.exposed.repositories.ResourceRepository
import com.esgi.nova.ports.provided.ITotalIterable
import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.required.IResourcePersistence
import com.google.inject.Inject

class ResourcePersistence @Inject constructor(
    private val resourceRepository: ResourceRepository,
    private val resourceMapper: ResourceMapper,
    private val dbContext: DatabaseContext
) : IResourcePersistence {
    override fun getAll(query: Query): List<ResourceDto> = dbContext.connectAndExec {
        resourceMapper.toDtos(resourceRepository.getAll(query))}
        override fun create(element: ResourceCmdDto): ResourceDto? {
            TODO("Not yet implemented")
        }

        override fun getAllTotal(query: Query): ITotalIterable<ResourceDto> {
            TODO("Not yet implemented")
        }
    }