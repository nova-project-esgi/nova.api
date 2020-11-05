package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toStaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.provided.services.IResourceService
import com.esgi.nova.ports.required.IResourcePersistence
import com.google.inject.Inject
import java.util.*

class ResourceService @Inject constructor(
    private val resourcePersistence: IResourcePersistence,
) : IResourceService {
    override fun getAll() = resourcePersistence.getAll()
    override fun create(element: ResourceCmdDto): ResourceDto? = resourcePersistence.create(element)
    override fun getOne(id: UUID): ResourceDto? = resourcePersistence.getOne(id)
    override fun getPage(pagination: IPagination): IPage<ResourceDto> =
        resourcePersistence.getAllTotal(pagination).toStaticPage(pagination)

    override fun updateOne(element: ResourceCmdDto, id: UUID): ResourceDto? = resourcePersistence.updateOne(element, id)


}