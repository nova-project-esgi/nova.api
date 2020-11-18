package com.esgi.nova.adapters.web.endpoints.translated_resources

import com.esgi.nova.ports.provided.dtos.ITranslation
import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/resources/{id}")
data class TranslatedResourceLocation(val id: UUID, override var language: String):ITranslation<String> {
}