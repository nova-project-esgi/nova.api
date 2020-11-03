package com.esgi.nova.domain.domain

import com.esgi.nova.ports.required.ITotalCollection

class EmptyTotalCollection<T>(override val total: Long = 0, elements: Collection<T> = listOf()) :
    ITotalCollection<T>(elements) {
}