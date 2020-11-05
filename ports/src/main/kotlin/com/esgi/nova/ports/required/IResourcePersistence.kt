package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import java.util.*

interface IResourcePersistence : IGetAll<ResourceDto>, ICreate<ResourceCmdDto, ResourceDto>,
    IGetAllTotal<ResourceDto>, IGetOne<UUID, ResourceDto>, IUpdateOne<ResourceCmdDto, UUID, ResourceDto> {
}