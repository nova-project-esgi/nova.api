package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import java.awt.Choice
import java.util.*

interface IChoiceResourceService : IGetAll<ChoiceResourceDto>, IGetPage<Choice>,
    ICreate<ChoiceResourceCmdDto, ChoiceResourceDto>, IGetOne<ChoiceResourcesKey, ChoiceResourceDto> {
    fun getAllByResourceId(resourceId: UUID): Collection<ChoiceResourceDto>
    fun getAllByChoiceId(choiceId: UUID): Collection<ChoiceResourceDto>
}