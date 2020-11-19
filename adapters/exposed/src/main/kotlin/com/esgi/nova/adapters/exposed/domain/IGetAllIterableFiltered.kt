package com.esgi.nova.adapters.exposed.domain

import org.jetbrains.exposed.sql.SizedIterable

interface IGetAllIterableFiltered<Filter, Output> {
    fun getAllFiltered(filter: Filter): SizedIterable<Output>
}

