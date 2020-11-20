package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceCmdDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.ChoiceResourcesKey
import java.util.*

interface IChoiceResourcePersistence :
    ICrudPersistence<ChoiceResourcesKey, ChoiceResourceCmdDto, ChoiceResourceDto> {
}