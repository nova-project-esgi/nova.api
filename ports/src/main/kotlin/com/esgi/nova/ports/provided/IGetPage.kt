package com.esgi.nova.ports.provided

interface IGetPage<T> {
    fun getPage(pagination: IPagination): IPage<T>
}