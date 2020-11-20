package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableByIds
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.IGetAllByIds

interface IPersistenceGetAllByIds<Id, OutputDto, OutputEntity>: IGetAllByIds<Id, OutputDto> {
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    val repository: IGetAllIterableByIds<Id, OutputEntity>
    fun getAllByIds(repository: IGetAllIterableByIds<Id, OutputEntity>, ids: Collection<Id>): Collection<OutputDto> =
            dbContext.connectAndExec { mapper.toDtos(repository.getAllByIds(ids).toList()) }
    override fun getAllByIds(ids: Collection<Id>): Collection<OutputDto> = getAllByIds(repository, ids)
}