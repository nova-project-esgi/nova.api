package com.esgi.nova.domain.services

import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.services.ICrudService
import com.esgi.nova.ports.required.ICrudPersistence
import java.util.*

abstract class BaseService<Id, Input, Output>(protected open val persistence: ICrudPersistence<Id, Input, Output>):
    ICrudService<Id, Input, Output> {
    override fun getAll() = persistence.getAll()
    override fun getOne(id: Id): Output? = persistence.getOne(id)
    override fun create(element: Input): Output? = persistence.create(element)
    override fun getPage(pagination: IPagination): IPage<Output> =
        persistence.getAllTotal(pagination).toPage(pagination)
    override fun updateOne(element: Input, id: Id): Output? = persistence.updateOne(element, id)
}


