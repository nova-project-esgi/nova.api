package com.esgi.nova.ports.provided.services

import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination

interface ICrudService<Id, Input, Output> {
    fun getAll(): Collection<Output>
    fun getOne(id: Id): Output?
    fun create(element: Input): Output?
    fun getPage(pagination: IPagination): IPage<Output>
    fun updateOne(element: Input, id: Id): Output?
}