package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.mappers.ResourceMapper
import com.esgi.nova.adapters.exposed.repositories.ResourceRepository
import com.esgi.nova.ports.provided.dtos.ResourceDto
import com.esgi.nova.ports.required.IResourcePersistence
import com.google.inject.Inject

class ResourcePersistence @Inject constructor(
    private val resourceRepository: ResourceRepository,
    private val resourceMapper: ResourceMapper
) : IResourcePersistence {
    override fun getAll(): List<ResourceDto> = resourceRepository.getAll().map { resourceMapper.toDto(it) }
}