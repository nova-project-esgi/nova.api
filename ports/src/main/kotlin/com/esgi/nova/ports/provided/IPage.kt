package com.esgi.nova.ports.provided

abstract class IPage<T>(elements: Collection<T>) : ArrayList<T>(elements) {
    abstract val pageSize: Int
    abstract val currentPage: Int
    abstract val hasNext: Boolean
    abstract val hasPrevious: Boolean
}
