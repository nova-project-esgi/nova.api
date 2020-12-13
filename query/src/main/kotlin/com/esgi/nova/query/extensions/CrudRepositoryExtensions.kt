package com.esgi.nova.query.extensions

import org.springframework.data.repository.CrudRepository

fun <T, ID> CrudRepository<T, ID>.findNullableById(id: ID): T? = findById(id).orElse(null)