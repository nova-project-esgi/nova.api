package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.IGetOne

interface IPersistenceGetOne<Id,OutputEntity, OutputDto>: IGetOne<Id, OutputDto> {
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    val repository: IGetOne<Id, OutputEntity>
    fun getOne(repository: IGetOne<Id, OutputEntity>, id: Id): OutputDto? =
            dbContext.connectAndExec { repository.getOne(id)?.let { choice -> mapper.toDto(choice) } }
    override fun getOne(id: Id): OutputDto? = getOne(repository, id)
}