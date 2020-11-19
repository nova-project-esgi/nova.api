package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.ports.required.ITotalCollection

interface IGetAllTotalFiltered<Filter, T> {
    fun getAllTotalFiltered(pagination: DatabasePagination, filter: Filter): ITotalCollection<T>
}