package com.esgi.nova.web.extensions

import com.esgi.nova.core_api.pagination.PageBase
import com.esgi.nova.web.pagination.PageMetadata

fun <T> PageBase<T>.toPageMetadata() = PageMetadata(this)
