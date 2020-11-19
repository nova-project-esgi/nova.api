package com.esgi.nova.domain.services.service

import com.esgi.nova.ports.common.IGetAllFiltered

interface IGetAllFilteredService<Filter, Output>: IGetAllFiltered<Filter, Output> {
    val persistence: IGetAllFiltered<Filter, Output>
    override fun getAllFiltered(filter: Filter) = persistence.getAllFiltered(filter)
}