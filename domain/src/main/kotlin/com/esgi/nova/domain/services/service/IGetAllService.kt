package com.esgi.nova.domain.services.service

import com.esgi.nova.ports.common.IGetAll

interface IGetAllService<Output> : IGetAll<Output> {
    val persistence: IGetAll<Output>
    override fun getAll() = persistence.getAll()
}