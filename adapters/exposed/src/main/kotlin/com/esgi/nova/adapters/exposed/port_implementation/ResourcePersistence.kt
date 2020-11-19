package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ResourceMapper
import com.esgi.nova.adapters.exposed.models.ResourceEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.repositories.ResourceRepository
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.required.IResourcePersistence
import com.google.inject.Inject
import java.util.*

class ResourcePersistence @Inject constructor(
    override val repository: ResourceRepository,
    mapper: ResourceMapper,
    dbContext: DatabaseContext
) : BasePersistence<UUID, ResourceCmdDto, ResourceEntity, ResourceDto>(repository, mapper, dbContext) ,IResourcePersistence {
}