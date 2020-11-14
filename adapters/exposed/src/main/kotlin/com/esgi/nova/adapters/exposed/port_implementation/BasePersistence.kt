package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.repositories.IRepository
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.required.ICrudPersistence
import com.esgi.nova.ports.required.ITotalCollection
import java.util.*

abstract class BasePersistence<Id, InputDto, OutputEntity, OutputDto>(
    protected open val repository: IRepository<Id, InputDto, OutputEntity>,
    protected val mapper: IDtoMapper<OutputEntity, OutputDto>,
    protected val dbContext: DatabaseContext
): ICrudPersistence<Id, InputDto, OutputDto> {
    override fun getAll(): Collection<OutputDto> =
        dbContext.connectAndExec { mapper.toDtos(repository.getAll().toList()) }

    override fun create(element: InputDto): OutputDto? =
        dbContext.connectAndExec { mapper.toDto(repository.create(element)) }

    override fun getAllTotal(pagination: IPagination): ITotalCollection<OutputDto> = dbContext.connectAndExec {
        val elements = repository.getAllTotal(DatabasePagination(pagination))
        TotalCollection(elements.total, mapper.toDtos(elements))
    }

    override fun getOne(id: Id): OutputDto? =
        dbContext.connectAndExec { repository.getOne(id)?.let { choice -> mapper.toDto(choice) } }

    override fun updateOne(element: InputDto, id: Id): OutputDto? = dbContext.connectAndExec {
        repository.updateOne(element, id)?.let { choice -> mapper.toDto(choice) }
    }

}