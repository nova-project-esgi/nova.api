package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.required.IGetAll

interface IResourceService: IGetAll<ResourceDto> {
}