package com.esgi.nova.common.extensions

import org.springframework.web.multipart.MultipartFile

fun MultipartFile.extractFileName(): String {
    this.originalFilename?.let {
        return it.withoutExtension()
    }
    return ""
}
