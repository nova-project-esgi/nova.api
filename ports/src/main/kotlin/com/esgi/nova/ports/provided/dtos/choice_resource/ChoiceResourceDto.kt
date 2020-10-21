package com.esgi.nova.ports.provided.dtos.choice_resource

import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import com.github.pozo.KotlinBuilder

class ChoiceResourceDto(var choice: ChoiceDto, var resouce: ResourceDto, var changeValue: Int)
