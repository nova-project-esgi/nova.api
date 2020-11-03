package com.esgi.nova.adapters.web.domain

import io.ktor.http.*

object CustomContentType {
    object Application {
        val ChoiceWithResource: ContentType = ContentType("application", "vnd+nova.choice_with_resources+json")
    }
}