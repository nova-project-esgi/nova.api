package com.esgi.nova.ports.provided

interface IPagination {
    val page: Long
    val size: Long
}