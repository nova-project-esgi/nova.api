package com.esgi.nova.adapters.exposed.repositories

import org.jetbrains.exposed.sql.SizedIterable

interface IGetAllIterable<T> {
    fun getAll(): SizedIterable<T>
}