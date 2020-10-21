package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto

interface IResourcePersistence: IGetAll<ResourceDto>, ICreate<ResourceCmdDto,ResourceDto>,
    IGetAllTotal<ResourceDto> {
}