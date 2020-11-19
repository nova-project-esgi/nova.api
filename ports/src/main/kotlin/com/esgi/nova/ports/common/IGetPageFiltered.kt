package com.esgi.nova.ports.common

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination

interface IGetPageFiltered<F,Output>{
    fun getPageFiltered(pagination: IPagination, filter: F): IPage<Output>
}