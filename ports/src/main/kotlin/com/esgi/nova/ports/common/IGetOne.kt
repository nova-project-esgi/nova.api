package com.esgi.nova.ports.common

interface IGetOne<ID, T> {
    fun getOne(id: ID): T?
}