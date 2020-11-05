package com.esgi.nova.ports.common

interface IUpdateOne<Changes,Id,Result> {
    fun updateOne(element: Changes, id: Id): Result?
}