package com.esgi.nova.domain.services

import com.esgi.nova.domain.domain.StaticPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.provided.services.ICrudService
import com.esgi.nova.ports.required.ICrudPersistence

abstract class BaseIntermediateMappingService<Id, Output, DetailedInput, DetailedOutput>(protected open val persistence: ICrudPersistence<Id, DetailedInput, DetailedOutput>) :
    ICrudService<Id, DetailedInput, Output> {

    protected abstract fun toFinalOutput(output: DetailedOutput): Output

    override fun getAll() = persistence.getAll().map { el -> toFinalOutput(el) }
    override fun getOne(id: Id): Output? {
        persistence.getOne(id)?.let { el -> return toFinalOutput(el) }
        return null;
    }

    override fun create(element: DetailedInput): Output? {
        persistence.create(element)?.let { el -> return toFinalOutput(el) }
        return null;
    }

    override fun getPage(pagination: IPagination): IPage<Output> {
        val total = persistence.getAllTotal(pagination)
        return StaticPage(
            total = total.total.toInt(),
            pageSize = pagination.size.toInt(),
            startPage = pagination.page.toInt(),
            elements = total.map { el -> toFinalOutput(el) })
    }

    override fun updateOne(element: DetailedInput, id: Id): Output? {
        persistence.updateOne(element, id)?.let { el -> return toFinalOutput(el) }
        return null
    }
}