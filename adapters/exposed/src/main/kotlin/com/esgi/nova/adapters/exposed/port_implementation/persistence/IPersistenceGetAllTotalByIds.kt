package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.ports.required.IGetAllTotalByIds
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.provided.IPagination
import java.util.*

interface IPersistenceGetAllTotalByIds<Id, OutputEntity, OutputDto>: IGetAllTotalByIds<Id, OutputDto> {
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    val repository: com.esgi.nova.adapters.exposed.domain.IGetAllTotalByIds<Id, OutputEntity>
    fun getAllTotalByIds(repository: com.esgi.nova.adapters.exposed.domain.IGetAllTotalByIds<Id, OutputEntity>, pagination: IPagination, ids: Collection<Id>)= dbContext.connectAndExec {
       val totalCollection =
                repository.getAllTotalByIds(DatabasePagination(pagination), ids)
        TotalCollection(
                totalCollection.total,
                mapper.toDtos(
                        totalCollection
                )
        )
    }
    override fun getAllTotalByIds( pagination: IPagination, ids: Collection<Id>)= getAllTotalByIds(repository, pagination, ids)
}