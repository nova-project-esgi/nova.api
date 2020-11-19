package com.esgi.nova.domain.services.service

import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.ports.common.IGetPageFiltered
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.IGetAllTotalFiltered

interface IGetPageFilteredService<Filter,Output> : IGetPageFiltered<Filter, Output> {
    val persistence: IGetAllTotalFiltered<Filter, Output>
    override fun getPageFiltered(pagination: IPagination, filter: Filter): IPage<Output> =
            persistence.getAllTotalFiltered(pagination, filter).toPage(pagination)
}