package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.ports.required.ITotalCollection
import java.util.*

interface IGetAllTotalByIds<Id, Output> {
    fun getAllTotalByIds(pagination: DatabasePagination, ids: Collection<Id>): ITotalCollection<Output>
}