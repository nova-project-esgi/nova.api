package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.domain.IRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.ICrudPersistence
import com.esgi.nova.ports.required.ITotalCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction

abstract class BasePersistence<Id, InputDto, OutputEntity, OutputDto>(
        override val repository: IRepository<Id, InputDto, OutputEntity>,
        override val mapper: IDtoMapper<OutputEntity, OutputDto>,
        override val dbContext: DatabaseContext
) : ICrudPersistence<Id, InputDto, OutputDto>,
        IPersistenceGetOne<Id, OutputEntity, OutputDto>,
        IPersistenceCreate<InputDto, OutputEntity, OutputDto>,
        IPersistenceUpdateOne<Id, InputDto, OutputEntity, OutputDto>,
        IPersistenceGetAll<OutputDto, OutputEntity>,
        IPersistenceGetAllTotal<OutputDto, OutputEntity>
{
    protected fun execAndConvertElements(transaction: ()->SizedIterable<OutputEntity>) = dbContext.connectAndExec {mapper.toDtos(transaction().toList())  }
    protected fun execAndConvertElement(transaction: ()->OutputEntity) = dbContext.connectAndExec {mapper.toDto(transaction())}
}