package com.esgi.nova.adapters.exposed.domain

import com.esgi.nova.ports.required.ITotalCollection

class TotalCollection<T>(override val total: Long, elements: Collection<T>) : ITotalCollection<T>(elements) {
}