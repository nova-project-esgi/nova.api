package com.esgi.nova.ports.common

interface IGetAllFiltered<T, U> {
    fun getAllFiltered(filter: T): Collection<U>
}