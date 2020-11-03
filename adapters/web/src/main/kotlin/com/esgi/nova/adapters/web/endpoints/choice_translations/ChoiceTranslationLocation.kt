package com.esgi.nova.adapters.web.endpoints.choice_translations

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("choices/{cId}/languages/{languageCodes}")
class ChoiceTranslationLocation(val cId: UUID, val languageCodes: String) {
}