package com.esgi.nova.ports.required

interface IGetOneDefault<Id, Output> {
    fun getOneDefault(id: Id): Output?
}