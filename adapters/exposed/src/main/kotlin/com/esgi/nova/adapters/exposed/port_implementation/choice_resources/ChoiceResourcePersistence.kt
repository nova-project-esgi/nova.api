package com.esgi.nova.adapters.exposed.port_implementation.choice_resources

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.ChoiceResourceMapper
import com.esgi.nova.adapters.exposed.models.ChoiceResourceEntity
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.repositories.choices_resources.ChoiceResourceByChoiceAndResourceRepository
import com.esgi.nova.adapters.exposed.repositories.choices_resources.ChoiceResourceRepository
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject

open class ChoiceResourcePersistence @Inject constructor(
        override val repository: ChoiceResourceByChoiceAndResourceRepository,
        mapper: ChoiceResourceMapper,
        dbContext: DatabaseContext
) : BasePersistence<ChoiceResourcesKey, ChoiceResourceCmdDto, ChoiceResourceEntity, ChoiceResourceDto>(
        repository,
        mapper,
        dbContext
), IChoiceResourcePersistence {


}