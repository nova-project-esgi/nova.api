package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.IGetOne

interface IPersistenceGetOne<Id,OutputEntity, OutputDto>: IGetOne<Id, OutputDto> {
    val repository: IGetOne<Id, OutputEntity>
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    override fun getOne(id: Id): OutputDto? =
            dbContext.connectAndExec { repository.getOne(id)?.let { choice -> mapper.toDto(choice) } }
}