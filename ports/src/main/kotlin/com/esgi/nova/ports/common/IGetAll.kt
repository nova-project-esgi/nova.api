package com.esgi.nova.ports.common

interface IGetAll<T> {
    fun getAll(): Collection<T>
}