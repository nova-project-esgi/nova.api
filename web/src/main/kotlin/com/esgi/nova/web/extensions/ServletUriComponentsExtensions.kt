package com.esgi.nova.web.extensions

import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder

fun ServletUriComponentsBuilder.withoutQueries(): UriComponentsBuilder {
    return ServletUriComponentsBuilder.fromCurrentRequestUri().replaceQueryParams(null)
}
