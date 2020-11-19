package com.esgi.nova.adapters.exposed.port_implementation.translation_persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.adapters.exposed.domain.TotalCollection
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.adapters.exposed.port_implementation.persistence.BasePersistence
import com.esgi.nova.adapters.exposed.domain.ITranslationRepository
import com.esgi.nova.ports.common.IGetAllById
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.ICrudPersistence
import com.esgi.nova.ports.required.IGetAllTotalById
import com.esgi.nova.ports.required.ITotalCollection
import java.util.*

abstract class BaseTranslationPersistence<Id, InputDto, OutputEntity, OutputDto>(
        override val repository: ITranslationRepository<Id, InputDto, OutputEntity>,
        mapper: IDtoMapper<OutputEntity, OutputDto>,
        dbContext: DatabaseContext
) : BasePersistence<Id, InputDto, OutputEntity, OutputDto>(repository, mapper, dbContext),
        ICrudPersistence<Id,InputDto, OutputDto>,
        IGetAllById<UUID, OutputDto>,
        IGetAllTotalById<UUID, OutputDto>
{
    override fun getAllTotalById(pagination: IPagination, language: UUID): ITotalCollection<OutputDto> = dbContext.connectAndExec {
        val elements = repository.getAllTotalByLanguage(DatabasePagination(pagination), language)
        TotalCollection(elements.total, mapper.toDtos(elements))
    }

    override fun getAllById(id: UUID): Collection<OutputDto> = dbContext.connectAndExec { mapper.toDtos(repository.getAllByLanguage(id).toList()) }

}