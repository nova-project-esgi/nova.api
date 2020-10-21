package com.esgi.nova.ports.provided

interface ITotalIterable<T> {
    val total: Int
    val elements: Iterable<T>
}