package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.domain.IGetAllIterable
import com.esgi.nova.ports.common.IGetAll

interface IPersistenceGetAll<OutputDto, OutputEntity>: IGetAll<OutputDto> {
    val repository: IGetAllIterable<OutputEntity>
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    override fun getAll(): Collection<OutputDto> =
            dbContext.connectAndExec { mapper.toDtos(repository.getAll().toList()) }

}