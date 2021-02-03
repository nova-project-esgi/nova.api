package com.esgi.nova.web.extensions

import com.esgi.nova.web.utils.HttpUtils
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder

fun UriComponentsBuilder.buildSslAwareness(): UriComponents{
    scheme(if(HttpUtils.isHttps()) "https" else "http" )
    return build()
}