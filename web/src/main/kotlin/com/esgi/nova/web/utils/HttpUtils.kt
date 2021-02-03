package com.esgi.nova.web.utils

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

object HttpUtils{
    fun isHttps(): Boolean {
        val requestAttributes = RequestContextHolder.getRequestAttributes()
        if (requestAttributes is ServletRequestAttributes) {
            return requestAttributes.request.isSecure
        }
        return false
    }
}