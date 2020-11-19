package com.esgi.nova.ports.common

interface IGetAllById<Id, Output> {
    fun getAllById(id: Id): Collection<Output>
}