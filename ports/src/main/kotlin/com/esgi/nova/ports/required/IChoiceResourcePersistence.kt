package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import java.util.*

interface IChoiceResourcePersistence : IGetAll<ChoiceResourceDto>, ICreate<ChoiceResourceCmdDto, ChoiceResourceDto>,
    IGetAllTotal<ChoiceResourceDto>, IGetOne<ChoiceResourcesKey, ChoiceResourceDto>,
    IUpdateOne<ChoiceResourceCmdDto, ChoiceResourcesKey, ChoiceResourceDto> {
    fun getAllByResourceId(resourceId: UUID): Collection<ChoiceResourceDto>
    fun getAllByChoiceId(choiceId: UUID): Collection<ChoiceResourceDto>
}