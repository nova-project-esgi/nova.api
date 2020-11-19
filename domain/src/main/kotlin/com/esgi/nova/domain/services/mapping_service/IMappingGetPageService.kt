package com.esgi.nova.domain.services.mapping_service

import com.esgi.nova.domain.domain.StaticPage
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.IGetAllTotal

interface IMappingGetPageService<Output, MappableOutput> : IGetPage<MappableOutput> {
    val persistence: IGetAllTotal<Output>
    val outputMapper: IMapping<Output, MappableOutput>
    override fun getPage(pagination: IPagination): IPage<MappableOutput> {
        val total = persistence.getAllTotal(pagination)
        return StaticPage(
                total = total.total.toInt(),
                pageSize = pagination.size.toInt(),
                startPage = pagination.page.toInt(),
                elements = total.mapNotNull { el -> outputMapper.map(el) })
    }
}