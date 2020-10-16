package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.ResourceDto

interface IResourcePersistence {
    fun getAll(): List<ResourceDto>
}