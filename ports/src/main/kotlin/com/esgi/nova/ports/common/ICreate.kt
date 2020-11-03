package com.esgi.nova.ports.common

interface ICreate<T, U> {
    fun create(element: T): U?
}