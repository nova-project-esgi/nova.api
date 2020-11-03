package com.esgi.nova.ports.required

interface IGetAllByIds<T, U> {
    fun getAllByIds(ids: List<T>): Collection<U>
}