package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import java.util.*

interface IResourcePersistence :
    ICrudPersistence<UUID, ResourceCmdDto, ResourceDto> {
}