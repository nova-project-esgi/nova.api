package com.esgi.nova.domain.services

import com.esgi.nova.domain.services.service.BaseService
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.provided.services.choices.IChoiceResourceService
import com.esgi.nova.ports.required.IChoiceResourcePersistence
import com.google.inject.Inject
import java.util.*

class ChoiceResourceService @Inject constructor(val persitence: IChoiceResourcePersistence) :
    BaseService<ChoiceResourcesKey, ChoiceResourceCmdDto, ChoiceResourceDto>(persitence),
        IChoiceResourceService {

    override fun getAllByResourceId(resourceId: UUID) = persitence.getAllByResourceId(resourceId)
    override fun getAllByChoiceId(choiceId: UUID): Collection<ChoiceResourceDto> =
        persitence.getAllByChoiceId(choiceId)

}