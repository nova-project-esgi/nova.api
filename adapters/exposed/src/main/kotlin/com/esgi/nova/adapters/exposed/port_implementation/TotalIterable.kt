package com.esgi.nova.adapters.exposed.port_implementation

import com.esgi.nova.ports.provided.ITotalIterable
import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class TotalIterable<T>(override val total: Int, override val elements: Iterable<T>) : ITotalIterable<T>