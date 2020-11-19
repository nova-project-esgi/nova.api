package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.IUpdateOne

interface IPersistenceUpdateOne<Id, InputDto, OutputEntity, OutputDto>: IUpdateOne<InputDto, Id, OutputDto> {
    val repository: IUpdateOne<InputDto, Id, OutputEntity>
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    override fun updateOne(element: InputDto, id: Id): OutputDto? = dbContext.connectAndExec {
        repository.updateOne(element, id)?.let { choice -> mapper.toDto(choice) }
    }
}