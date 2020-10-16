package com.esgi.nova.domain.services

import com.esgi.nova.ports.provided.services.IResourceService
import com.esgi.nova.ports.required.IResourcePersistence
import com.google.inject.Inject

class ResourceService @Inject constructor(private val resourcePersistence: IResourcePersistence) : IResourceService {
    override fun getAll() = resourcePersistence.getAll()
}