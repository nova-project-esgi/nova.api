package com.esgi.nova.ports.provided.dtos.resource

import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import com.github.pozo.KotlinBuilder
import java.util.*

class ResourceCmdDto(var name: String, var choices: List<ChoiceDto>)
