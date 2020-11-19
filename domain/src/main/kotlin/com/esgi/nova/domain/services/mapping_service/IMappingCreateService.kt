package com.esgi.nova.domain.services.mapping_service

import com.esgi.nova.ports.common.ICreate

interface IMappingCreateService<Input,Output, MappableOutput> : ICreate<Input, MappableOutput> {
    val persistence: ICreate<Input, Output>
    val outputMapper: IMapping<Output, MappableOutput>
    override fun create(element: Input): MappableOutput? {
        persistence.create(element)?.let { el -> return outputMapper.map(el) }
        return null
    }
}