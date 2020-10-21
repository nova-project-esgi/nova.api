package com.esgi.nova.ports.required

interface IGetOne<ID,T> {
    fun getOne(id: ID): T?
}