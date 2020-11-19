package com.esgi.nova.ports.common

interface IGetAllFiltered<Filter, Output> {
    fun getAllFiltered(filter: Filter): Collection<Output>
}