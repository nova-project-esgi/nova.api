package com.esgi.nova.ports.common

interface IGetAllByIds<T, U> {
    fun getAllByIds(ids: List<T>): Collection<U>
}