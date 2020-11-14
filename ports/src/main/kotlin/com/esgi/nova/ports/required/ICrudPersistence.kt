package com.esgi.nova.ports.required

import com.esgi.nova.ports.provided.IPagination

interface ICrudPersistence<Id, InputDto, OutputDto> {

    fun getAll(): Collection<OutputDto>

    fun create(element: InputDto): OutputDto?

    fun getAllTotal(pagination: IPagination): ITotalCollection<OutputDto>

    fun getOne(id: Id): OutputDto?
    fun updateOne(element: InputDto, id: Id): OutputDto?
}