package com.esgi.nova.ports.required

abstract class ITotalCollection<T>(elements: Collection<T>) : ArrayList<T>(elements) {
    abstract val total: Long
}