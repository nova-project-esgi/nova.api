package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import java.util.*

interface IChoicePersistence  :
    ICrudPersistence<UUID, ChoiceCmdDto, ChoiceDto> {
}