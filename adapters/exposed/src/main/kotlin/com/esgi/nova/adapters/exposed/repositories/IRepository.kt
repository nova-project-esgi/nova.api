package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.models.ChoiceEntity
import com.esgi.nova.ports.common.ICreate
import com.esgi.nova.ports.common.IGetOne
import com.esgi.nova.ports.common.IUpdateOne
import com.esgi.nova.ports.provided.dtos.choice.commands.ChoiceCmdDto
import java.util.*

interface IRepository<Id, Input,  Output>: IGetAllIterable<Output>, IGetOne<Id, Output>, ICreate<Input, Output>,
    IGetAllTotal<Output>, IUpdateOne<Input, Id, Output> {
}