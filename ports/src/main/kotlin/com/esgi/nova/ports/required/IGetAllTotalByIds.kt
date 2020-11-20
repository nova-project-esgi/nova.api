package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination

interface IGetAllTotalByIds<Id, Output> {
    fun getAllTotalByIds(pagination: IPagination, ids: Collection<Id>): ITotalCollection<Output>
}