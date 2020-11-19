package com.esgi.nova.domain.services.service

import com.esgi.nova.ports.common.IGetOne

interface IGetOneService<ID, Output> : IGetOne<ID, Output> {
    val persistence: IGetOne<ID, Output>;
    override fun getOne(id: ID) = persistence.getOne(id)
}