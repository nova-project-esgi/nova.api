package com.esgi.nova.adapters.exposed.mappers

import extensions.getInsideType
import org.jetbrains.exposed.dao.Entity
import org.mapstruct.BeforeMapping
import org.mapstruct.MappingTarget
import org.mapstruct.TargetType
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*


class CycleAvoidingMappingContext(private val ignoreIterable: Boolean = true) {
    private val knownInstances: MutableMap<Any, Any> = IdentityHashMap()
    @BeforeMapping
    fun <T> blockListMapping(sourceList: Iterable<*>, @TargetType target: Class<T>): T? {
        if(ignoreIterable){
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