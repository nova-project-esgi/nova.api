package com.esgi.nova.adapters.web.domain

import io.ktor.http.*
import kotlin.reflect.full.memberProperties

object CustomContentType {
    object Application {
        val ChoiceWithResource: ContentType = ContentType("", "vnd+nova.choice_with_resources+json")
        val ChoiceNavigation: ContentType = ContentType("application", "vnd+nova.choice_navigation+json")
        val TranslatedEvent: ContentType = ContentType("application", "vnd+nova.translated_event+json")
        val TranslatedChoice: ContentType = ContentType("application", "vnd+nova.translated_choice+json")
        val TranslatedChoiceResource: ContentType = ContentType("application", "vnd+nova.translated_choice_resource+json")
        val TranslatedResource: ContentType = ContentType("application", "vnd+nova.translated_resource+json")
    }

    fun getTypes(): List<ContentType> {
        return Application::class.memberProperties.map { p -> p.get(Application) as ContentType}
    }
}
