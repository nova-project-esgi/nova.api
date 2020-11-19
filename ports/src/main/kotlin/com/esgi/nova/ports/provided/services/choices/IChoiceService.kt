package com.esgi.nova.ports.provided.services.choices

import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceWithResourcesCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.services.ICrudService
import java.util.*

interface IChoiceService : ICrudService<UUID, ChoiceCmdDto, ChoiceDto> {
    fun createChoiceAndAttachResources(element: ChoiceWithResourcesCmdDto): ChoiceDto?
}