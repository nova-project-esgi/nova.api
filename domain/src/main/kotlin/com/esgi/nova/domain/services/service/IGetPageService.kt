package com.esgi.nova.domain.services.service

import com.esgi.nova.domain.extensions.toPage
import com.esgi.nova.ports.provided.IGetPage
import com.esgi.nova.ports.provided.IPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.IGetAllTotal

interface IGetPageService<Output> : IGetPage<Output> {
    val persistence: IGetAllTotal<Output>
    override fun getPage(pagination: IPagination): IPage<Output> =
            persistence.getAllTotal(pagination).toPage(pagination)
}