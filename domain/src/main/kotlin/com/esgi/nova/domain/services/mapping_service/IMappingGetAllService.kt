package com.esgi.nova.domain.services.mapping_service

import com.esgi.nova.ports.common.IGetAll

interface IMappingGetAllService<Output, MappableOutput> : IGetAll<MappableOutput> {
    val persistence: IGetAll<Output>
    val outputMapper: IMapping<Output, MappableOutput>
    override fun getAll() = persistence.getAll().mapNotNull { el -> outputMapper.map(el) }
}