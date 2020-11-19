package com.esgi.nova.adapters.exposed.domain

import org.jetbrains.exposed.sql.SizedIterable

interface IGetAllIterableById<Id, Output> {
    fun getAllById(id: Id): SizedIterable<Output>
}