package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.ports.required.ITotalCollection

interface IGetAllTotal<T> {
    fun getAllTotal(pagination: DatabasePagination): ITotalCollection<T>
}