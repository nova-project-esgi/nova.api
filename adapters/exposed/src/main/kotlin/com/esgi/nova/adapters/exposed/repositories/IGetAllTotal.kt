package com.esgi.nova.adapters.exposed.repositories

import com.esgi.nova.adapters.exposed.domain.DatabasePagination
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.ITotalCollection

interface IGetAllTotal<T> {
    fun getAllTotal(pagination: DatabasePagination): ITotalCollection<T>
}