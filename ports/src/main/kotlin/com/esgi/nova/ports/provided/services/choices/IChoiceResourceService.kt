package com.esgi.nova.ports.provided.services.choices

import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import com.esgi.nova.ports.provided.services.ICrudService
import java.util.*

interface IChoiceResourceService : ICrudService<ChoiceResourcesKey, ChoiceResourceCmdDto, ChoiceResourceDto> {
    fun getAllByResourceId(resourceId: UUID): Collection<ChoiceResourceDto>
    fun getAllByChoiceId(choiceId: UUID): Collection<ChoiceResourceDto>
}