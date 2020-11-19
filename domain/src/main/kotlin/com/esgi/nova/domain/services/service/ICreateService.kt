package com.esgi.nova.domain.services.service

import com.esgi.nova.ports.common.ICreate

interface ICreateService<Input, Output> : ICreate<Input, Output> {
    val persistence: ICreate<Input, Output>
    override fun create(element: Input): Output? = persistence.create(element)
}