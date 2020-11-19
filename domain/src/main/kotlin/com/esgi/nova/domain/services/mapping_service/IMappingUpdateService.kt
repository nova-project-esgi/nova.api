package com.esgi.nova.domain.services.mapping_service

import com.esgi.nova.ports.common.IUpdateOne

interface IMappingUpdateService<Input, Id, Output, MappableOutput>: IUpdateOne<Input, Id, MappableOutput> {
    val persistence: IUpdateOne<Input, Id, Output>
    val outputMapper: IMapping<Output, MappableOutput>
    override fun updateOne(element: Input, id: Id): MappableOutput? {
        persistence.updateOne(element, id)?.let { el -> return outputMapper.map(el) }
        return null
    }
}