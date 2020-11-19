package com.esgi.nova.domain.services.mapping_service

import com.esgi.nova.ports.common.IGetOne

interface IMappingGetOneService<Id, Output, MappableOutput> : IGetOne<Id, MappableOutput> {
    val persistence: IGetOne<Id, Output>
    val outputMapper: IMapping<Output, MappableOutput>
    override fun getOne(id: Id): MappableOutput? {
        persistence.getOne(id)?.let { el -> return outputMapper.map(el) }
        return null
    }
}