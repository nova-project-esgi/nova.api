package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.domain.IGetAllTotalFiltered
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.ITotalCollection

interface IPersistenceGetAllTotalFiltered<Filter, OutputDto, OutputEntity>: com.esgi.nova.ports.required.IGetAllTotalFiltered<Filter, OutputDto>{
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    val repository: IGetAllTotalFiltered<Filter, OutputEntity>
    fun getAllTotalFiltered(repository: IGetAllTotalFiltered<Filter, OutputEntity>, pagination: IPagination, filter: Filter): ITotalCollection<OutputDto> = dbContext.connectAndExec {
        val elements = repository.getAllTotalFiltered(DatabasePagination(pagination), filter)
        TotalCollection(elements.total, mapper.toDtos(elements))
    }
    override fun getAllTotalFiltered(pagination: IPagination, filter: Filter): ITotalCollection<OutputDto>  = getAllTotalFiltered(repository, pagination, filter)
}