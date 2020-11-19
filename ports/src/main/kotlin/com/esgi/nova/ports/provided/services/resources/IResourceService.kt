package com.esgi.nova.ports.provided.services.resources

import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.provided.services.ICrudService
import java.util.*

interface IResourceService : ICrudService<UUID, ResourceCmdDto, ResourceDto> {

}