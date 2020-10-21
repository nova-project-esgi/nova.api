package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.dtos.choice.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto

interface IChoicePersistence: IGetAll<ChoiceDto>, ICreate<ChoiceCmdDto,ChoiceDto>, IGetAllTotal<ChoiceDto> {
}