package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableFiltered
import com.esgi.nova.ports.common.IGetAllFiltered

interface IPersistenceGetAllFiltered<Filter, OutputDto, OutputEntity>: IGetAllFiltered<Filter, OutputDto> {
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    val repository: IGetAllIterableFiltered<Filter, OutputEntity>
    fun getAllFiltered(repository: IGetAllIterableFiltered<Filter, OutputEntity>, filter: Filter): Collection<OutputDto> =
            dbContext.connectAndExec { mapper.toDtos(repository.getAllFiltered(filter).toList()) }
    override fun getAllFiltered(filter: Filter): Collection<OutputDto> = getAllFiltered(repository, filter)
}