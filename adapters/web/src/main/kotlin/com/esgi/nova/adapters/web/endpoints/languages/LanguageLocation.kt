package com.esgi.nova.adapters.web.endpoints.languages

import io.ktor.locations.*
import java.util.*

@Location("/languages/{id}")
data class LanguageLocation(val id: UUID) {
}
