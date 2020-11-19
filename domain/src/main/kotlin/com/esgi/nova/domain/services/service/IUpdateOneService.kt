package com.esgi.nova.domain.services.service

import com.esgi.nova.ports.common.IUpdateOne

interface IUpdateOneService<Changes, Id, Output> : IUpdateOne<Changes, Id, Output> {
    val persistence: IUpdateOne<Changes, Id, Output>
    override fun updateOne(element: Changes, id: Id): Output? = persistence.updateOne(element, id)
}