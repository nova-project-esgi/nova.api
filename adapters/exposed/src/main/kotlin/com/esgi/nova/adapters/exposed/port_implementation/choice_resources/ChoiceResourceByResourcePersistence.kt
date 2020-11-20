package com.esgi.nova.adapters.exposed.port_implementation.choice_resources

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceResourceMapper
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.port_implementation.persistence.IPersistenceGetAllById
import com.esgi.nova.adapters.exposed.repositories.choices_resources.ChoiceResourceByResourceRepository
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.google.inject.Inject
import java.util.*

class ChoiceResourceByResourcePersistence @Inject constructor(
        override val repository: ChoiceResourceByResourceRepository,
        mapper: ChoiceResourceMapper,
        dbContext: DatabaseContext) : BasePersistence<UUID, ChoiceResourceCmdDto, ChoiceResourceEntity, ChoiceResourceDto>(
        repository,
        mapper,
        dbContext
),
        IPersistenceGetAllById<UUID, ChoiceResourceDto, ChoiceResourceEntity> {

    override fun getAllById(id: UUID): Collection<ChoiceResourceDto> = getAllById(repository, id)

}