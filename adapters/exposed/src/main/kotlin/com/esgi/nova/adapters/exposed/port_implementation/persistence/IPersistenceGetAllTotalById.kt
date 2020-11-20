package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.IGetAllTotalById
import com.esgi.nova.ports.required.ITotalCollection
import java.util.*

interface IPersistenceGetAllTotalById<Id, OutputEntity, OutputDto>: IGetAllTotalById<Id, OutputDto> {
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    val repository: com.esgi.nova.adapters.exposed.domain.IGetAllTotalById<Id, OutputEntity>
    fun getAllTotalById(repository: com.esgi.nova.adapters.exposed.domain.IGetAllTotalById<Id, OutputEntity>, pagination: IPagination, id: Id): ITotalCollection<OutputDto> = dbContext.connectAndExec {
        val elements = repository.getAllTotalById(DatabasePagination(pagination), id)
        TotalCollection(elements.total, mapper.toDtos(elements))
    }

    override fun getAllTotalById( pagination: IPagination, id: Id): ITotalCollection<OutputDto> = getAllTotalById(repository, pagination, id)
}