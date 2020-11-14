package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceResourceMapper
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.repositories.ChoiceResourceRepository
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.util.*

class ChoiceResourcePersistence @Inject constructor(
    override val repository: ChoiceResourceRepository,
    mapper: ChoiceResourceMapper,
    dbContext: DatabaseContext
) : BasePersistence<ChoiceResourcesKey, ChoiceResourceCmdDto, ChoiceResourceEntity, ChoiceResourceDto>(
    repository,
    mapper,
    dbContext
), IChoiceResourcePersistence {

    override fun getAllByResourceId(resourceId: UUID) = dbContext.connectAndExec {
        mapper.toDtos(
            repository.getAllByResourceId(resourceId).toList()
        )
    }

    override fun getAllByChoiceId(choiceId: UUID): Collection<ChoiceResourceDto> = dbContext.connectAndExec {
        mapper.toDtos(
            repository.getAllByChoiceId(choiceId).toList()
        )
    }

}