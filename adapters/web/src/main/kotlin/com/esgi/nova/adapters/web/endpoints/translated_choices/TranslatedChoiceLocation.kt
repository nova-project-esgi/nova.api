package com.esgi.nova.adapters.web.endpoints.translated_choices

import com.esgi.nova.ports.provided.dtos.ITranslation
import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/choices/{id}")
data class TranslatedChoiceLocation(val id: UUID, override var language: String): ITranslation<String> {
}