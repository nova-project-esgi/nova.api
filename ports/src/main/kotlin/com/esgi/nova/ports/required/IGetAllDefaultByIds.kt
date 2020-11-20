package com.esgi.nova.ports.required

interface IGetAllDefaultByIds<Id, Output> {
    fun getAllDefaultByIds(ids: Collection<Id>): Collection<Output>
}