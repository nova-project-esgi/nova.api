package com.esgi.nova.ports.provided.filters

interface IFilterMultiple<T> {
    val entities: Collection<T>
}

