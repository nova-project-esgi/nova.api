package com.esgi.nova.adapters.exposed.mappers

import org.mapstruct.BeforeMapping
import org.mapstruct.MappingTarget
import org.mapstruct.TargetType
import java.util.*


class CycleAvoidingMappingContext(
    private var ignoreAllIterable: Boolean = true,
    private var ignoreFirstLevelIterable: Boolean = true
) {
    private val knownInstances: MutableMap<Any, Any> = IdentityHashMap()

    @BeforeMapping
    fun <T> blockListMapping(sourceIter: Iterable<*>, @TargetType target: Class<T>): T? {
        if (!ignoreFirstLevelIterable) {
            ignoreFirstLevelIterable = !ignoreFirstLevelIterable
            ignoreAllIterable = true
            return null
        }
        if (ignoreAllIterable) {
            return listOf<T>() as T
        }

        return null
    }


    @BeforeMapping
    fun <T> getMappedInstance(source: Any, @TargetType target: Class<T>): T {
        return knownInstances[source] as T
    }

    @BeforeMapping
    fun storeMappedInstance(source: Any, @MappingTarget target: Any) {
        knownInstances[source] = target
    }

}