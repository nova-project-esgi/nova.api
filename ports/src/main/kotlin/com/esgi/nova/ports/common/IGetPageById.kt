package com.esgi.nova.ports.common

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination

interface IGetPageById<Id,Output> {
    fun getPageById(pagination: IPagination, id: Id): IPage<Output>
}