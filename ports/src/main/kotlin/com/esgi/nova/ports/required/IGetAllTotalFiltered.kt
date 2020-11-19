package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination

interface IGetAllTotalFiltered<Filter,Output>{
    fun getAllTotalFiltered(pagination: IPagination, filter: Filter): ITotalCollection<Output>
}