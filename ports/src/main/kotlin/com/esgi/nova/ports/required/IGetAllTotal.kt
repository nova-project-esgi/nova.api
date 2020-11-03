package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination

interface IGetAllTotal<T> {
    fun getAllTotal(pagination: IPagination): ITotalCollection<T>
}