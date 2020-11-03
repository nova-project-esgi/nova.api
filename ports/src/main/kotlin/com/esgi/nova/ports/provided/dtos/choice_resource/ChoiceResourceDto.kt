package com.esgi.nova.ports.provided.dtos.choice_resource

import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto

class ChoiceResourceDto(var choice: ChoiceDto, var resource: ResourceDto, override var changeValue: Int) :
    IChoiceResource
