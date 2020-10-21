package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.Query

interface IGetAll<T> {
    fun getAll(query: Query):List<T>
}