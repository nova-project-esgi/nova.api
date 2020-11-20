package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableById
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.IGetAllById
import java.util.*

interface IPersistenceGetAllById<ID, OutputDto, OutputEntity>: IGetAllById<ID, OutputDto> {
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    val repository: IGetAllIterableById<ID, OutputEntity>
    fun getAllById(repository: IGetAllIterableById<ID, OutputEntity>, id: ID): Collection<OutputDto> =  dbContext.connectAndExec { mapper.toDtos(repository.getAllById(id).toList()) }
    override fun getAllById(id: ID): Collection<OutputDto> =  getAllById(repository, id)
}