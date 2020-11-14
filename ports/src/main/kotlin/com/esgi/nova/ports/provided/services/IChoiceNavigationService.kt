package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceNavigationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationCmdDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationDto
import com.esgi.nova.ports.provided.dtos.choice_translation.ChoiceTranslationKey
import java.util.*

interface IChoiceNavigationService : ICrudService<UUID, ChoiceCmdDto,ChoiceNavigationDto> {
}