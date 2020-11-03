package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.provided.dtos.choice.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import java.util.*

interface IChoicePersistence : IGetAll<ChoiceDto>, ICreate<ChoiceCmdDto, ChoiceDto>, IGetAllTotal<ChoiceDto>,
    IGetOne<UUID, ChoiceDto> {
    fun getAllByEventId(eventId: UUID): Collection<ChoiceDto>
}