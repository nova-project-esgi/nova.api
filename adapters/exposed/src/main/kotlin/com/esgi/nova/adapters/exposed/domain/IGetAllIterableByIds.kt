package com.esgi.nova.adapters.exposed.domain

import org.jetbrains.exposed.sql.SizedIterable

interface IGetAllIterableByIds<Id, Output> {
    fun getAllByIds(ids: Collection<Id>): SizedIterable<Output>
}