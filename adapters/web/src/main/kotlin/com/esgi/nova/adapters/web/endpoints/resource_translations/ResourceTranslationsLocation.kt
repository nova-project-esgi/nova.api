package com.esgi.nova.adapters.web.endpoints.resource_translations

import io.ktor.locations.*
import java.util.*

@KtorExperimentalLocationsAPI
@Location("/resources/{rId}/languages/{codes}")
data class ResourceTranslationsLocation(val rId: UUID, val codes: String) {
}