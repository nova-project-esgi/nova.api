package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.dtos.ResourceDto

interface IResourceService {
    fun getAll(): List<ResourceDto>
}