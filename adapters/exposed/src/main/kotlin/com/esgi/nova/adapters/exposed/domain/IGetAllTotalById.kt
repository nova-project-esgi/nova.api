package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.ports.required.ITotalCollection

interface IGetAllTotalById<Id, Output> {
    fun getAllTotalById(pagination: DatabasePagination, id: Id): ITotalCollection<Output>
}