package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.resource.ResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceCmdDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import com.esgi.nova.ports.provided.dtos.resource.translated_resource_detailed.TranslatedResourceDetailedDto
import java.util.*

interface IResourceService : IGetAll<ResourceDto>, ICreate<ResourceCmdDto, ResourceDto>, IGetOne<UUID, ResourceDto>,
    IGetPage<ResourceDto>, IUpdateOne<ResourceCmdDto, UUID, ResourceDto> {

}