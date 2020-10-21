package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.Query
import com.esgi.nova.ports.provided.ITotalIterable

interface IGetAllTotal<T> {
    fun getAllTotal(query: Query): ITotalIterable<T>
}