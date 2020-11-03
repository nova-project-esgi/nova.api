package com.esgi.nova.domain.extensions

import com.esgi.nova.domain.domain.Page
import com.esgi.nova.domain.domain.StaticPage
import com.esgi.nova.ports.provided.IPagination
import com.esgi.nova.ports.required.ITotalCollection

fun <T> ITotalCollection<T>.toPage(pageSize: Int, page: Int) =
    Page(pageSize, page, this)

fun <T> ITotalCollection<T>.toPage(pagination: IPagination) =
    Page(pagination.size.toInt(), pagination.page.toInt(), this)

fun <T> ITotalCollection<T>.toStaticPage(pagination: IPagination) =
    StaticPage(pagination.size.toInt(), pagination.page.toInt(), this)