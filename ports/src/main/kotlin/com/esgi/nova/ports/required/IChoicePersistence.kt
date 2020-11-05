package com.esgi.nova.ports.required

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import java.util.*

interface IChoicePersistence : IGetAll<ChoiceDto>, ICreate<ChoiceCmdDto, ChoiceDto>, IGetAllTotal<ChoiceDto>,
    IGetOne<UUID, ChoiceDto>, IUpdateOne<ChoiceCmdDto, UUID, ChoiceDto> {
    fun getAllByEventId(eventId: UUID): Collection<ChoiceDto>
}