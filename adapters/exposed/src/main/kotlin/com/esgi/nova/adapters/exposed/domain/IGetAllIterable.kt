package com.esgi.nova.adapters.exposed.domain

import org.jetbrains.exposed.sql.SizedIterable

interface IGetAllIterable<T> {
    fun getAll(): SizedIterable<T>
}