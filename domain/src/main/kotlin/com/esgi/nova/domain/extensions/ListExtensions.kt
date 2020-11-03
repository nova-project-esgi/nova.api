package com.esgi.nova.domain.extensions

import com.esgi.nova.domain.domain.StaticPage
import com.esgi.nova.ports.provided.IPagination

fun <E> List<E>.toStaticPage(pagination: IPagination, total: Int): StaticPage<E> {
    return StaticPage(pagination.size.toInt(), pagination.page.toInt(), total, this)
}