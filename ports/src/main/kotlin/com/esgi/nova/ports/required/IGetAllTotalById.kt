package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination

interface IGetAllTotalById<Id, Output> {
    fun getAllTotalById(pagination: IPagination, id: Id): ITotalCollection<Output>
}