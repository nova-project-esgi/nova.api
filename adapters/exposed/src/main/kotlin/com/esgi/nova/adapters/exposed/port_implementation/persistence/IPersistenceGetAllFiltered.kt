package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.domain.IGetAllIterableFiltered
import com.esgi.nova.ports.common.IGetAllFiltered

interface IPersistenceGetAllFiltered<Filter, OutputDto, OutputEntity>: IGetAllFiltered<Filter, OutputDto> {
    val repository: IGetAllIterableFiltered<Filter, OutputEntity>
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    override fun getAllFiltered(filter: Filter): Collection<OutputDto> =
            dbContext.connectAndExec { mapper.toDtos(repository.getAllFiltered(filter).toList()) }

}