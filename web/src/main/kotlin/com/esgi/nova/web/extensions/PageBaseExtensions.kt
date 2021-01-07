package com.esgi.nova.web.extensions

import com.esgi.nova.application.pagination.PageMetadata
import com.esgi.nova.core_api.pagination.PageBase

fun <T> PageBase<T>.toPageMetadata() = PageMetadata(this)
