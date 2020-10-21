package com.esgi.nova.ports.required

interface ICreate<T,U > {
    fun create(element: T): U?
}