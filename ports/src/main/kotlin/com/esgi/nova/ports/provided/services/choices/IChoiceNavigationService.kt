package com.esgi.nova.ports.provided.services.choices

import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceNavigationDto
import com.esgi.nova.ports.provided.services.ICrudService
import java.util.*

interface IChoiceNavigationService : ICrudService<UUID, ChoiceCmdDto, ChoiceNavigationDto> {
}