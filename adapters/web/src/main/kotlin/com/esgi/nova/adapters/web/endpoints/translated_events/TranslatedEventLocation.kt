package com.esgi.nova.adapters.web.endpoints.translated_events

import com.esgi.nova.ports.provided.dtos.ITranslation
import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/events/{id}")
data class TranslatedEventLocation(val id: UUID, override var language: String): ITranslation<String> {}
