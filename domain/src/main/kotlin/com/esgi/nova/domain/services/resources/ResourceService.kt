package com.esgi.nova.domain.services.resources

import com.esgi.nova.domain.services.service.BaseService
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.provided.services.resources.IResourceService
import com.esgi.nova.ports.required.IResourcePersistence
import com.google.inject.Inject
import java.util.*

class ResourceService @Inject constructor(
    override val persistence: IResourcePersistence,
) : BaseService<UUID, ResourceCmdDto, ResourceDto>(persistence), IResourceService {

}