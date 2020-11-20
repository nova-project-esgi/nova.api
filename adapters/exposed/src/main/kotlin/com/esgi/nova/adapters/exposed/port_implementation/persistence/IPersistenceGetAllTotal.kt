package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.domain.IGetAllTotal
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.ITotalCollection

interface IPersistenceGetAllTotal<OutputDto, OutputEntity>: com.esgi.nova.ports.required.IGetAllTotal<OutputDto>{
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    val repository: IGetAllTotal<OutputEntity>
    fun getAllTotal(repository: IGetAllTotal<OutputEntity>, pagination: IPagination): ITotalCollection<OutputDto> = dbContext.connectAndExec {
        val elements = repository.getAllTotal(DatabasePagination(pagination))
        TotalCollection(elements.total, mapper.toDtos(elements))
    }
    override fun getAllTotal(pagination: IPagination): ITotalCollection<OutputDto> = getAllTotal(repository, pagination)
}