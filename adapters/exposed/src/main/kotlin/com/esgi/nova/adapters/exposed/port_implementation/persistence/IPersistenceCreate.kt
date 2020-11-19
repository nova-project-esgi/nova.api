package com.esgi.nova.adapters.exposed.port_implementation.persistence

import com.esgi.nova.adapters.exposed.DatabaseContext
import com.esgi.nova.adapters.exposed.mappers.IDtoMapper
import com.esgi.nova.ports.common.ICreate

interface IPersistenceCreate<InputDto, OutputEntity, OutputDto>: ICreate<InputDto, OutputDto> {
    val repository: ICreate<InputDto, OutputEntity>
    val mapper: IDtoMapper<OutputEntity, OutputDto>
    val dbContext: DatabaseContext
    override fun create(element: InputDto): OutputDto? =
            dbContext.connectAndExec { mapper.toDto(repository.create(element)) }
}