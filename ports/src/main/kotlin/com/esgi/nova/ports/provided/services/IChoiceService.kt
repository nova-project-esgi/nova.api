package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetAll
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceWithResourcesCmdDto
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import java.util.*

interface IChoiceService : IGetAll<ChoiceDto>, IGetOne<UUID, ChoiceDto>, ICreate<ChoiceCmdDto, ChoiceDto>,
    IGetPage<ChoiceDto>, IUpdateOne<ChoiceCmdDto, UUID, ChoiceDto> {
    fun createChoiceAndAttachResources(element: ChoiceWithResourcesCmdDto): ChoiceDto?
}