package com.esgi.nova.ports.provided.dtos.resource

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import java.util.*

open class ResourceDto(override var id: UUID, var choices: List<ChoiceDto>) : IId<UUID>
